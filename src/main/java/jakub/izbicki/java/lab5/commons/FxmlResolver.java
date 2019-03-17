package jakub.izbicki.java.lab5.commons;

import org.apache.commons.lang.StringUtils;

final class FxmlResolver {

    private static final String CONTROLLER_KEYWORD = "Controller";

    private static final String FXML_FORMAT_PATH = "/fxml/%s.fxml";

    private FxmlResolver() {
    }

    static String from(Class<? extends Controller> controller) {
        return String.format(FXML_FORMAT_PATH, getName(controller));
    }

    private static String getName(Class<? extends Controller> controller) {
        return controller.getSimpleName().replaceFirst(CONTROLLER_KEYWORD, StringUtils.EMPTY);
    }
}
