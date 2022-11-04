# Get information directories - Java ☕
### Hey 👋 this is a simple code that generate a report with information of the directory
#### ⚠ Only works with command line console or IDE 
#### Language messages: Spanish
#### Methods documented in english

## Features ⚙
- Generate report with name and date, all subfolders and files
- You can customice report name
- The report will be generate with .txt

## Clone with git 📎
```
git clone https://github.com/ShxwZ/StorageReport.git
```

## How it works? 👀
### You write the path of the directory and then the name you want to put to the report :)
### (The report will be generate on directory folder specified 📂)
### Example:
![](https://github.com/ShxwZ/StorageReport/blob/master/ReadmeResources/GenerateReport.gif)
![](https://github.com/ShxwZ/StorageReport/blob/master/ReadmeResources/Report.jpg)

## Updated (24/10/2022) method to be more useful
```java
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
```

#### You can use the code as you like 😊
