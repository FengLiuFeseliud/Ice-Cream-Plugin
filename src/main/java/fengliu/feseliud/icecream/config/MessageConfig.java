package fengliu.feseliud.icecream.config;

/**
 * 消息语言配置文件
 */
public class MessageConfig extends Config implements IConfig{
    public static final String MESSAGE_PATH = "message";
    public static final String DEFAULT_LANGUAGE = "zh_cn";
    private String language;

    public MessageConfig(){
        super(MESSAGE_PATH, null);
    }

    public MessageConfig(String subPath){
        super(MESSAGE_PATH + "/" + subPath, null);
    }

    /**
     * 设置语言
     * @param language 语言
     * @return 消息语言配置文件对像
     */
    public MessageConfig setLanguage(String language){
        this.language = language;
        return this;
    }

    @Override
    public String getName() {
        return this.language + ".yml";
    }
}
