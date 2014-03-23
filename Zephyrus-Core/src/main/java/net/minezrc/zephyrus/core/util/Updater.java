package net.minezrc.zephyrus.core.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import net.minezrc.zephyrus.Zephyrus;
import net.minezrc.zephyrus.core.config.ConfigOptions;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 * Check dev.bukkit.org to find updates for a given plugin, and download the
 * updates if needed.
 * <p/>
 * <b>VERY, VERY IMPORTANT</b>: Because there are no standards for adding
 * auto-update toggles in your plugin's config, this system provides NO CHECK
 * WITH YOUR CONFIG to make sure the user has allowed auto-updating. <br>
 * It is a <b>BUKKIT POLICY</b> that you include a boolean value in your config
 * that prevents the auto-updater from running <b>AT ALL</b>. <br>
 * If you fail to include this option in your config, your plugin will be
 * <b>REJECTED</b> when you attempt to submit it to dev.bukkit.org.
 * <p/>
 * An example of a good configuration option would be something similar to
 * 'auto-update: true' - if this value is set to false you may NOT run the
 * auto-UpdateChecker. <br>
 * If you are unsure about these rules, please read the plugin submission
 * guidelines: http://goo.gl/8iU5l
 * 
 * @author Gravity Modified by Minnymin3 to be more lightweight and work with
 *         Zephyrus file naming
 * @version 2.0
 */

public class Updater {

	private Plugin plugin;
	private String versionName;
	private String versionLink;
	private String versionType;
	private String versionGameVersion;
	private URL url;
	private Thread thread;

	private String apiKey = null; 
	private static final String TITLE_VALUE = "name";
	private static final String LINK_VALUE = "downloadUrl";
	private static final String TYPE_VALUE = "releaseType";
	private static final String VERSION_VALUE = "gameVersion";
	private static final String QUERY = "/servermods/files?projectIds=";
	private static final String HOST = "https://api.curseforge.com";
	private static final String[] NO_UPDATE_TAG = { "-DEV", "-PRE", "-SNAPSHOT" };
	
	private YamlConfiguration config;
	private Updater.UpdateResult result = Updater.UpdateResult.DISABLED;

	/**
	 * Gives the dev the result of the update process. Can be obtained by called
	 * getResult().
	 */
	public enum UpdateResult {
		/**
		 * The updater did not find an update, and nothing was downloaded.
		 */
		NO_UPDATE,
		/**
		 * The server administrator has disabled the updating system
		 */
		DISABLED,
		/**
		 * For some reason, the updater was unable to contact dev.bukkit.org to
		 * download the file.
		 */
		FAIL_DBO,
		/**
		 * When running the version check, the file on DBO did not contain the a
		 * version in the format 'vVersion' such as 'v1.0'.
		 */
		FAIL_NOVERSION,
		/**
		 * The id provided by the plugin running the updater was invalid and
		 * doesn't exist on DBO.
		 */
		FAIL_BADID,
		/**
		 * The server administrator has improperly configured their API key in
		 * the configuration
		 */
		FAIL_APIKEY,
		/**
		 * The updater found an update, but because of the UpdateType being set
		 * to NO_DOWNLOAD, it wasn't downloaded.
		 */
		UPDATE_AVAILABLE
	}

	public static Updater update() {
		return new Updater(Zephyrus.getPlugin(), 56632);
	}
	
	/**
	 * Initialize the updater
	 * 
	 * @param plugin The plugin that is checking for an update.
	 * @param id The dev.bukkit.org id of the project
	 */
	private Updater(Plugin plugin, int id) {
		this.plugin = plugin;

		final File pluginFile = plugin.getDataFolder().getParentFile();
		final File updaterFile = new File(pluginFile, "Updater");
		final File updaterConfigFile = new File(updaterFile, "config.yml");

		if (!updaterFile.exists()) {
			updaterFile.mkdir();
		}
		if (!updaterConfigFile.exists()) {
			try {
				updaterConfigFile.createNewFile();
			} catch (final IOException e) {
				plugin.getLogger().severe("The updater could not create a configuration in "
						+ updaterFile.getAbsolutePath());
				e.printStackTrace();
			}
		}
		this.config = YamlConfiguration.loadConfiguration(updaterConfigFile);

		this.config
				.options()
				.header("This configuration file affects all plugins using the Updater system (version 2+ - http://forums.bukkit.org/threads/96681/ )"
						+ '\n'
						+ "If you wish to use your API key, read http://wiki.bukkit.org/ServerMods_API and place it below."
						+ '\n'
						+ "Some updating systems will not adhere to the disabled value, but these may be turned off in their plugin's configuration.");
		this.config.addDefault("api-key", "PUT_API_KEY_HERE");
		this.config.addDefault("disable", false);

		if (this.config.get("api-key", null) == null) {
			this.config.options().copyDefaults(true);
			try {
				this.config.save(updaterConfigFile);
			} catch (final IOException e) {
				plugin.getLogger().severe("The updater could not save the configuration in "
						+ updaterFile.getAbsolutePath());
				e.printStackTrace();
			}
		}

		if (this.config.getBoolean("disable") || !ConfigOptions.UPDATE_CHECKING) {
			this.result = UpdateResult.DISABLED;
			return;
		}

		String key = this.config.getString("api-key");
		if (key.equalsIgnoreCase("PUT_API_KEY_HERE") || key.equals("")) {
			key = null;
		}

		this.apiKey = key;

		try {
			this.url = new URL(Updater.HOST + Updater.QUERY + id);
		} catch (final MalformedURLException e) {
			this.result = UpdateResult.FAIL_BADID;
			e.printStackTrace();
		}

		this.thread = new Thread(new UpdateRunnable());
		this.thread.start();
	}

	/**
	 * Get the result of the update process.
	 */
	public Updater.UpdateResult getResult() {
		this.waitForThread();
		return this.result;
	}

	/**
	 * Get the latest version's release type (release, beta, or alpha).
	 */
	public String getLatestType() {
		this.waitForThread();
		return this.versionType;
	}

	/**
	 * Get the latest version's game version.
	 */
	public String getLatestGameVersion() {
		this.waitForThread();
		return this.versionGameVersion;
	}

	/**
	 * Get the latest version's name.
	 */
	public String getLatestName() {
		this.waitForThread();
		return this.versionName;
	}

	/**
	 * Get the latest version's file link.
	 */
	public String getLatestFileLink() {
		this.waitForThread();
		return this.versionLink;
	}

	/**
	 * As the result of Updater output depends on the thread's completion, it is
	 * necessary to wait for the thread to finish before allowing anyone to
	 * check the result.
	 */
	private void waitForThread() {
		if ((this.thread != null) && this.thread.isAlive()) {
			try {
				this.thread.join();
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Check to see if the program should continue by evaluation whether the
	 * plugin is already updated, or shouldn't be updated
	 */
	private boolean versionCheck(String title) {
		final String version = this.plugin.getDescription().getVersion().replace("-SNAPSHOT", "");
		if (title.split("-").length == 2) {
			final String remoteVersion = title.split("-")[1].split(" ")[0];
			if (this.hasTag(version) || version.equalsIgnoreCase(remoteVersion)
					|| getVersionHeritage(version, remoteVersion) >= 0) {
				this.result = Updater.UpdateResult.NO_UPDATE;
				return false;
			}
			System.out.println(getVersionHeritage(version, remoteVersion));
		} else {
			this.result = Updater.UpdateResult.FAIL_NOVERSION;
			return false;
		}
		return true;
	}

	/**
	 * Evaluate whether the version number is marked showing that it should not
	 * be updated by this program
	 */
	private boolean hasTag(String version) {
		for (final String string : Updater.NO_UPDATE_TAG) {
			if (version.contains(string)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Added by minnymin3
	 * 
	 * @param str1 Version of plugin
	 * @param str2 Remote version
	 * @return -1 if the plugin version is lower, 0 if it is equal, 1 if the
	 *         plugin version is greater
	 */
	private int getVersionHeritage(String str1, String str2) {
		String[] vals1 = str1.split("\\.");
		String[] vals2 = str2.split("\\.");
		int i = 0;
		while (i < vals1.length && i < vals2.length && vals1[i].equals(vals2[i])) {
			i++;
		}
		if (i < vals1.length && i < vals2.length) {
			int diff = Integer.valueOf(vals1[i]).compareTo(Integer.valueOf(vals2[i]));
			return diff < 0 ? -1 : diff == 0 ? 0 : 1;
		}
		return vals1.length < vals2.length ? -1 : vals1.length == vals2.length ? 0 : 1;
	}

	private boolean read() {
		try {
			final URLConnection conn = this.url.openConnection();
			conn.setConnectTimeout(5000);

			if (this.apiKey != null) {
				conn.addRequestProperty("X-API-Key", this.apiKey);
			}
			conn.addRequestProperty("User-Agent", "Updater (by Gravity)");

			conn.setDoOutput(true);

			final BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final String response = reader.readLine();

			final JSONArray array = (JSONArray) JSONValue.parse(response);

			if (array.size() == 0) {
				this.result = UpdateResult.FAIL_BADID;
				return false;
			}

			this.versionName = (String) ((JSONObject) array.get(array.size() - 1)).get(Updater.TITLE_VALUE);
			this.versionLink = (String) ((JSONObject) array.get(array.size() - 1)).get(Updater.LINK_VALUE);
			this.versionType = (String) ((JSONObject) array.get(array.size() - 1)).get(Updater.TYPE_VALUE);
			this.versionGameVersion = (String) ((JSONObject) array.get(array.size() - 1)).get(Updater.VERSION_VALUE);

			return true;
		} catch (final IOException e) {
			if (e.getMessage().contains("HTTP response code: 403")) {
				this.result = UpdateResult.FAIL_APIKEY;
			} else {
				this.result = UpdateResult.FAIL_DBO;
			}
			e.printStackTrace();
			return false;
		}
	}

	private class UpdateRunnable implements Runnable {
		@Override
		public void run() {
			if (Updater.this.url != null) {
				if (Updater.this.read()) {
					if (Updater.this.versionCheck(Updater.this.versionName)) {
						Updater.this.result = UpdateResult.UPDATE_AVAILABLE;
					}
				}
			}
		}
	}
}