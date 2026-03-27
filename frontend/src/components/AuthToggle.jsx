import React from "react";

export default function AuthToggle({ mode, setMode }) {
    return (
        <div
            className="
                relative w-72 h-12 
                bg-card/40 dark:bg-white/10
                border border-border dark:border-white/20
                rounded-2xl flex p-1
                backdrop-blur-xl
                transition-all duration-300
            "
        >

            {/* Sliding highlight */}
            <div
                className={`
                    absolute top-1 left-1 h-10 w-1/2 rounded-xl
                    bg-primary shadow-lg
                    transition-all duration-300
                    ${mode === "register" ? "translate-x-full" : ""}
                `}
            />

            {/* Login */}
            <button
                onClick={() => setMode("login")}
                className={`
                    relative w-1/2 z-10 font-semibold
                    transition-colors duration-300
                    ${mode === "login"
                        ? "text-primary-foreground"
                        : "text-muted-foreground hover:text-foreground"
                    }
                `}
            >
                Login
            </button>

            {/* Register */}
            <button
                onClick={() => setMode("register")}
                className={`
                    relative w-1/2 z-10 font-semibold
                    transition-colors duration-300
                    ${mode === "register"
                        ? "text-primary-foreground"
                        : "text-muted-foreground hover:text-foreground"
                    }
                `}
            >
                Register
            </button>

        </div>
    );
}