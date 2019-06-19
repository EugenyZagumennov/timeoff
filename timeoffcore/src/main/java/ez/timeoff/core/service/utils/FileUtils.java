package ez.timeoff.core.service.utils;

/**
 * Created on 18-Jun-19.
 *
 * @author Evgeniy Zagumennov
 */
public class FileUtils{
    public static final String FILES_PATH = "files";
    public static final String AVATAR_FILE_NAME = "avatar.png";

    public static String getAvatarFile(String login){
        return String.format("/%s/%s/%s", FILES_PATH, login, AVATAR_FILE_NAME);
    }

    public static String getAvatarDirectory(String login){
        return String.format("/%s/%s/", FILES_PATH, login);
    }
}
