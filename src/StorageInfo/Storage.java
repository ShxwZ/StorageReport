package StorageInfo;

import inputmanager.InputManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Gabriel
 * @version 15/10/2022
 */
public class Storage {
    List<File> fileList = new ArrayList<>();
    List<File> foldersList = new ArrayList<>();

    /**
     * Get info from folder
     * @param route Path of folder
     */
    public void getInfoFolder(String route) {
        getNumberOfFiles(new File(route));
        getNumberOfFolders(new File(route));
        createFile(route);

    }

    /**
     * Get number of files
     * @param directory Folder
     * @return int: number of files
     */
    public int getNumberOfFiles(File directory) {
        File [] folder = directory.listFiles();
        int counter = 0;
        if (folder != null) {
            for (File file : folder) {
                counter += file.isDirectory() ? getNumberOfFiles(file) : 1;
                if (!file.isDirectory())
                    fileList.add(file);
            }
        }
        return counter;
    }

    /**
     * Get number of folders
     * @param directory Folder
     * @return int: count of folders
     */
    public int getNumberOfFolders(File directory) {
        File [] folder = directory.listFiles();
        int counter = 0;
        if (folder != null) {
            for (File file : folder) {
                if (file.isDirectory()) {
                    counter += 1 + getNumberOfFolders(file);
                    foldersList.add(file);
                }
            }
        }
        return counter;
    }

    /**
     * Create file with information of the folder
     * @param route Path of folder
     */
    public void createFile(String route) {
        File file = new File(route + "/" + InputManager.getStringNotEmpty("nombre para el informe") + ".txt");
        List<File> files = new ArrayList<>(fileList);
        List<File> folders = new ArrayList<>(foldersList);
        try (PrintWriter newArchive = new PrintWriter(new FileWriter(file))){
            newArchive.println("=".repeat(60));
            newArchive.println("Nombre: " + file.getName() + " - Fecha: " + Calendar.getInstance().getTime());
            newArchive.println("=".repeat(60));
            newArchive.println("> Número total de archivos: " + files.size());
            newArchive.println("> Número total de subcarpetas: " + folders.size());
            newArchive.println("");
            newArchive.println("==[Listado de carpetas]==");
            folders.forEach(folder -> newArchive.printf("Nombre carpeta: %-15s Path: %-65s Número archivos: %d\n",
                                                        folder.getName(),folder.getPath(),getNumberOfFiles(folder)));
            newArchive.println("");
            newArchive.println("==[Listado de archivos]==");
            files.forEach(file_ -> newArchive.printf("Nombre: %-30s Path: %-65s\n" ,
                                                    file_.getName(),file_.getPath()));
            System.out.println("[+] Informe generado en " + route + "!");
        } catch (IOException e) {
            System.err.println("[ERROR] El path introducido no es valido!");
        }

    }

}