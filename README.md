# WadLoader2
A Doom/ Doom2 GZDoom WadLoader which can manage your Wad Collection.

You can manage your Wads with Tags and custom Wad-Packs.

You have to set the Environemt Variable %GZDoom_Home% to the directory where your gzdoom.exe file is located.

## Layers

* 0 Plugins
  * Database
  * GUI

* 1 Adapters
  * Presenters
  * Controller
  * Gateways

* 2 Application Code
  * Use Cases

* 3 Domain Code
  * Entities, etc.

* 4 Abstraction Code
  * Utility, concepts, generic functions 

## Aggregates

* WadPack
  * WadPackTag
* Wad
  * WadTag 
  * DefaultTag
* IWad
  * IWadTag 
  * DefaultTag
* CustomTag
* ContainsMinTag
* ContainsMaxTag
* ExclusiveTagRule
* ExclusiveWadRule

## UseCases
* load Wads from a set dir into the WadLoader (x)
* create WadPacks (x)
* add Tags to Wads and WadPacks (x)
* add rules for WadPacks (x)
  * templates for the rules (x)
* generate a List of all Wads (x)
  *  group the list in sublists via a given criteria (tag) (x)
* set Environment variable for the GZDoom launcher (x)
* Start GZDoom with selected Wad/ WadPack (x)

## Wad/ IWad/ Wad-Pack (WadConfig) search
* enter search string to filter the WadConfigs with
* if you write "<example>" only configs containing <example> will be shown
* if you prefix your term with "d:<exaple>" only configs with parent-folder paths containing <example> will be shown
  * hint: does not work on Wad-Packs because they consist of multiple Wads
* if you prefix your term with "c:<exaple>" only configs with custom tags containing <example> will be shown
* if none of the above is true, results matching any of the above will be shown