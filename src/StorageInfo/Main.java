package StorageInfo;

import inputmanager.InputManager;

public class Main {
    public static void main(String[] args) {
        Storage sManager = new Storage();
        sManager.getInfoFolder(InputManager.getStringNotEmpty("el path de la carpeta a examinar"));
    }
}
