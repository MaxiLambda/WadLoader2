# WadLoader2
A Doom/ Doom2 GZDoom WadLoader which can manage your Wad Collection.

You can manage your Wads with Tags and custom Wad-Packs.

You have to set the Environemt Variable %GZDoom_Home% to the directory where your gzdoom.exe file is located.

## Layers

* 0 Plugins
  * Database
  * GUI

* 2 Application Code
  * Use Cases

* 3 Domain Code
  * Entities, etc.

* 4 Abstraction Code
  * Utility, concepts, generic functions 

## Aggregates

* WadPack
  * WadPackTag
  * WadPackName
  * WadLoadOrder
    * WadLoadOrderId
* Wad
  * WadTag
  * DefaultTag
  * WadPath
* IWad
  * IWadTag 
  * DefaultTag
  * IWadPath
* CustomTag
* ContainsMinTag
* ContainsMaxTag
* ExclusiveTagRule

## Wad/ IWad/ Wad-Pack (WadConfig) search
* enter search string to filter the WadConfigs with
* if you write "<example>" only configs with names containing <example> will be shown
* if you prefix your term with "d:<exaple>" only configs with parent-folder paths containing <example> will be shown
  * hint: does not work on Wad-Packs because they consist of multiple Wads
* if you prefix your term with "c:<exaple>" only configs with custom tags containing <example> will be shown
* if none of the above is true, results matching any of the above will be shown
