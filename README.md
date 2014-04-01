Zephyrus
========

Zephyrus is an all in one magic plugin. It has many magical features such as spells, items and enchantments that are meant to be used on survival and RPG servers alike. It supports economies, worldguard, permissions, and more! It also has an API that allows other developers to create add-ons. The name is pronounced Zef-er-uhs and comes from the Greek god of the West Wind.

Zephyrus is licenced under GNU General Public Licence version 3

#### Jenkins Build Server

[![Build Status](http://ci.minezrc.net/job/Zephyrus-II/badge/icon)](http://ci.minezrc.net/job/Zephyrus-II/)

#### Referencing API
When referencing Zephyrus, it is best to reference the API as it is more lightweight and easier to manage. Download the Zephyrus-API jar from the [Continues Integration Server](http://ci.minezrc.net/job/Zephyrus-II/lastStableBuild/) or from the [releases section](https://github.com/minnymin3/Zephyrus-II/releases) of the Github repository.

Maven:

    <repositories>
      <repository>
        <id>zephyrus</id>
        <url>http://ci.minezrc.net/plugin/repository/everything/</url>
      </repository>
    </repositories>

    <dependencies>
      <dependency>
        <groupid>net.minezrc.zephyrus</groupid>
        <artifactId>Zephyrus-API</artifactId>
        <version>2.0.0-SNAPSHOT</version>
        <scope>provided</scope>
      </dependency>
    </dependencies>


#### BukkitDev Page

http://dev.bukkit.org/bukkit-plugins/zephyrus/
