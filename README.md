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
* set Environment variable for the GZDoom launcher
* Start GZDoom with selected Wad/ WadPack