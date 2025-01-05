package dev.svyas.connections_service.auth;

public class UserContextHolder {

    private static final ThreadLocal<Long> userContext = new ThreadLocal<>();

    public static Long getUserId(){
        return userContext.get();
    }

    static void setUserContext(Long userId){
        userContext.set(userId);
    }

    static void clear(){
        userContext.remove();
    }

}
