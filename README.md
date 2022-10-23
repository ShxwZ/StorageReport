# Get information directories - Java ☕
### Hey 👋 this is a simple code that generate a report with information of the directory
#### Only works with command line console or IDE
#### Language: Spanish

## How it works?
### You write the path of the directory and then the name you want to put to the report :)
### (24/10/2022) Now create a directory for the report!
### Example:
[]!()

## Update (24/10/2022) ✔
#### This method is now more reusable
````
    public int getNumberOfTypeArchive(File directory, int type) {
        File [] folder = directory.listFiles();
        int counter = 0;
        if (folder != null) {
            for (File file : folder) {
                if (type == FILES){
                    counter += file.isDirectory() ? getNumberOfTypeArchive(file,type) : 1;
                    if (!file.isDirectory())
                        fileList.add(file);
                } else if (type == FOLDERS){
                    if (file.isDirectory()) {
                        counter += 1 + getNumberOfTypeArchive(file,type);
                        foldersList.add(file);
                    }
                }
            }
        }
        return counter;
    }
````