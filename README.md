# memex-gui-insert

Program allows user to insert entities to database in [Indental](https://wiki.xxiivv.com/site/indental.html) format, using a simple gui window. 

## Current features

- Saving entity with title, type, note, quote, file, link, tags into ndtl file in memex directory
- opening said database in browser after saving
- validating passsed values such as links or files

## Building
To install project simply execute:
```
git clone --recursive https://github.com/mga44/memex-gui-insert.git
cd 
mvn install
```
In Your `/target` directory You should have:
* jar file
* `/resources` directory

Edit `resources/app.properties` file and set directory to Your memex instance.

## TODO
- [ ] add multiline parameter support
- [ ] add remaining parameters such as:
  - [ ] "done" mark
  - [ ] author
  - [ ] project
  - [ ] term
  - [ ] person
  - [ ] "revised" mark
  - [ ] source
- [x] improve UI
- [ ] caching and autofilling tags
- [ ] logging errors to file
- [ ] integrations with some kind of hosting platforms

## Acknowledgements

* [Personal knowledge database repo by Korymen](https://github.com/kormyen/memex)
