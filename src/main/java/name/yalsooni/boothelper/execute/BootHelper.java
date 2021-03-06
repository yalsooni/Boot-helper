package name.yalsooni.boothelper.execute;


import name.yalsooni.boothelper.classloader.JarLoader;
import name.yalsooni.boothelper.classloader.exception.JARFileNotFoundException;
import name.yalsooni.boothelper.classloader.exception.NotDirectoryException;
import name.yalsooni.boothelper.repository.BootHelperRepository;
import name.yalsooni.boothelper.util.Log;
import name.yalsooni.boothelper.util.reader.PropertyReader;

/**
 * boot helper start class
 * Created by ijyoon on 2016. 7. 29..
 */
public class BootHelper {

    private PropertyReader bootHelperProperties = new PropertyReader();
    private JarLoader jarLoader;

    private String[] args;

    public PropertyReader getPropertyReader() {
        return this.bootHelperProperties;
    }

    public JarLoader createInstanceJarLoader(String libPath) throws NotDirectoryException, JARFileNotFoundException {
        this.jarLoader = new JarLoader(libPath);
        BootHelperRepository.setBootHelper(this);
        return this.jarLoader;
    }

    public ClassLoader getClassLoader() {
        return this.jarLoader.getClassLoader();
    }

    public String[] getArgs() {
        return this.args;
    }

    /**
     * 실행
     *
     * @param args
     * @throws Exception
     */
    public void exe(String[] args) throws Exception {
        this.args = args;

        Log.console(" - BootHelper Meta Loading..");
        BootHelperUtil.metaLoading(this);

        Log.console(" - User Property Loading..");
        BootHelperUtil.propertyLoading(this);

        Log.console(" - Class Loader Loading..");
        BootHelperUtil.classLoad(this);

        Log.console(" - Execute Main Class..");
        BootHelperUtil.classExecute(this);


    }
}