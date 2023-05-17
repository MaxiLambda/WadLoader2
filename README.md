# WadLoader2
GZDoom WadLoader which can manage your Wad Collection.

You can manage your Wads with Tags and custom Wad-Packs.

## Setup
You have to set the Environment Variable %GZDoom_Home% to the directory where your gzdoom.exe file is located.
You have to load your IWad/Wad files into the WadLoader by selecting the folders which contain your files.
You have to use separate folders for your Wads and IWads. It is advised to use a common root folder for your Wads,
that way it is easier to select all folders which contain Wads (selecting the root folder is enough).

You can get GZDoom (here)[https://zdoom.org/downloads]. 
### IWads
You need IWads to play, you can buy the game or use some free alternatives. 
Free and legal IWads Replacements for Doom 1/2 can be found [here](https://github.com/freedoom/freedoom/releases/download/v0.12.1/freedoom-0.12.1.zip).

### Wads
If you want to spice up things you can start to experiment with some Wads.
A great place to start are the (Cacoaward-winners)[https://www.doomworld.com/cacowards/]
### Example WadPack
An example WadPack using doom1/freedom1 could use the following wads:
* [PHOBOS: ANOMALY REBORN](https://www.doomworld.com/idgames/levels/doom/Ports/p-r/par-lutz)
* [Trailblazer](https://forum.zdoom.org/viewtopic.php?t=47494) 

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

## Wad/ IWad/ Wad-Pack (WadConfig) search
* enter search string to filter the WadConfigs with
* if you write "<example>" only configs containing <example> will be shown
* if you prefix your term with "d:<exaple>" only configs with parent-folder paths containing <example> will be shown
  * hint: does not work on Wad-Packs because they consist of multiple Wads
* if you prefix your term with "c:<exaple>" only configs with custom tags containing <example> will be shown
* if none of the above is true, results matching any of the above will be shown
