import { createContext, useContext, useState } from "react";

interface UserContextType {
    user: user_t | null;
    setUser: (user: user_t) => void;
    isLoggedIn: () => Boolean;
    logout: () => void;
}

const UserContext = createContext<UserContextType | null>(null);

export const useUser = () => {
    const context = useContext(UserContext);
    if (!context) {
        throw new Error("useUser must be used within a UserProvider");
    }
    return context;
}

export const UserProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
    const [user, setUser] = useState<user_t | null>(null);

    const isLoggedIn = () => {
        return (user !== null);
    }

    const logout = () => {
        setUser(null);
    }

    return (
        <UserContext.Provider 
            value={{ user, setUser, isLoggedIn, logout }}
        >
            {children}
        </UserContext.Provider>
    );
}