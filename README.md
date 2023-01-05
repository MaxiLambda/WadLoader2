# WadLoader2
A Doom/ Doom2 GZDoom WadLoader which can manage your Wad Collection.

You can manage your Wads with Tags and custom Wad-Packs.

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

## UseCases
* load Wads from a set dir into the WadLoader
* create WadPacks
* add Tags to Wads and WadPacks
* add rules for WadPacks
  * templates for the rules 
* generate a List of all Wads
  *  group the list in sublists via a given criteria (tag)