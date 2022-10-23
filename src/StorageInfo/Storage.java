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
    final int FOLDERS = 1;
    final int FILES = 2;

    /**
     * Get info from folder
     * @param route Path of folder
     */
    public void getInfoFolder(String route) {
        getNumberOfTypeArchive(new File(route), FILES);
        getNumberOfTypeArchive(new File(route), FOLDERS);
        createFile();

    }

    /**
     * Get number of files or folders
     * @param directory Folder
     * @param type FOLDERS or FILES
     * @return int: number of files or folders
     */
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


    /**
     * Create file with information of the folder
     */
    public void createFile() {
        String routeReport = InputManager.getStringNotEmpty("el directorio para el informe");
        File newFolder = new File(routeReport);
        if (!newFolder.exists()) newFolder.mkdirs();
        File file = new File(routeReport + "/" + InputManager.getStringNotEmpty("nombre para el informe") + ".txt");
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
                                                        folder.getName(),folder.getPath(), getNumberOfTypeArchive(folder,FILES)));
            newArchive.println("");
            newArchive.println("==[Listado de archivos]==");
            files.forEach(file_ -> newArchive.printf("Nombre: %-30s Path: %-65s\n" ,
                                                    file_.getName(),file_.getPath()));
            System.out.println("[+] Informe generado en " + routeReport + "!");
        } catch (IOException e) {
            System.err.println("[ERROR] El path introducido no es valido!");
        }

    }

}