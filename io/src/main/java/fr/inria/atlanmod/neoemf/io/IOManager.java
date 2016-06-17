package fr.inria.atlanmod.neoemf.io;

import fr.inria.atlanmod.neoemf.io.impl.AbstractInternalHandler;
import fr.inria.atlanmod.neoemf.io.input.Reader;

import java.io.File;

/**
 *
 */
public class IOManager {

    private IOManager() {
    }

    public static void importFromFile(File file, Class<? extends Reader> readerType, PersistenceHandler persistenceHandler) throws Exception {
        Reader reader = readerType.newInstance();
        AbstractInternalHandler internalHandler = reader.getHandlerClass().newInstance();
        internalHandler.addHandler(persistenceHandler);
        reader.addHandler(internalHandler);

        reader.read(file);
    }

}
