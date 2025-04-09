package Data;

import org.testng.annotations.DataProvider;

public class UserData {

    @DataProvider(name = "allUsers")
    public static Object[][] provideUsers() {
        return new Object[][] {
                {"standard_user", "secret_sauce"},
                {"locked_out_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"},
                {"error_user", "secret_sauce"},
                {"visual_user", "secret_sauce"}
        };
    }

    @DataProvider(name = "workingUsers")
    public static Object[][] provideWorkingUsers() {
        return new Object[][] {
                {"standard_user", "secret_sauce"},
                {"visual_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"},
                {"problem_user", "secret_sauce"}
        };
    }

    @DataProvider(name = "UserData")
    public static Object[][] UserData() {
        return new Object[][] {
                {"standard_user", "secret_sauce", true},
                {"problem_user", "secret_sauce", true},
                {"performance_glitch_user", "secret_sauce", true},
                {"error_user", "secret_sauce", true},
                {"visual_user", "secret_sauce", true},
                {"locked_out_user", "secret_sauce", false}
        };
    }


}
