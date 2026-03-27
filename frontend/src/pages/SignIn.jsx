import React, { useEffect, useState } from 'react'
import { useLocation, useNavigate } from 'react-router-dom';
import { useFetch } from '@/helpers/useFetch';
import { useAuth } from '@/helpers/useAuth';
import AuthToggle from '@/components/AuthToggle';
import { Button } from '@/components/ui/button';
import { ChevronLeftIcon } from 'lucide-react';

function SignIn() {
    const [username, setUserName] = useState("");
    const [password, setPassword] = useState("");
    const [email, setEmail] = useState("");
    const [mode, setMode] = useState("login");
    const navigate = useNavigate();
    const location = useLocation();

    const fromLocation = location.state?.from;

    const from = fromLocation
        ? fromLocation.pathname + fromLocation.search
        : "/";

    const { login } = useAuth();

    const { data, loading, error, refetch } = useFetch(
        `/api/auth/${mode}`,
        {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
        },
        false
    );

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (mode === "login") {
            await refetch({
                body: JSON.stringify({ username, password })
            });
        }

        if (mode === "register") {
            await refetch({
                body: JSON.stringify({ username, password, email })
            });
        }


    };

    useEffect(() => {
        if (data?.accessToken) {
            login({
                token: data.accessToken,
                userName: data.username,
            });
            console.log(from);

            navigate(from, { replace: true });
        }
    }, [data, login, navigate, from]);

    return (

        <>

            <Button
                className='relative top-6 left-6'
                onClick={() => navigate('/')}
            >
                <ChevronLeftIcon /> Back
            </Button>
            <div className="min-h-screen flex items-center justify-center bg-background">

                <div className="
                w-full max-w-md backdrop-blur-xl
                bg-card/60 dark:bg-white/10
                border border-border dark:border-white/20
                shadow-2xl rounded-3xl p-10
                transition-all duration-500
                ">

                    <div className="mb-8 flex justify-center">
                        <AuthToggle mode={mode} setMode={setMode} />
                    </div>

                    <h2 className="text-3xl font-bold text-foreground text-center mb-8">
                        {mode === "login" ? "Welcome Back" : "Create Account"}
                    </h2>

                    <form onSubmit={handleSubmit} className="flex flex-col gap-5">

                        {mode === "register" && (
                            <input
                                type="email"
                                placeholder="Email"
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}
                                className="
                            w-full px-5 py-3 rounded-2xl
                            bg-card/30 text-foreground
                            placeholder-muted-foreground
                            outline-none focus:ring-2 focus:ring-primary
                            transition
                            "
                            />
                        )}

                        <input
                            data-testid="signin-username"
                            type="text"
                            placeholder="Username"
                            value={username}
                            onChange={(e) => setUserName(e.target.value)}
                            className="
                        px-5 py-3 rounded-2xl
                        bg-card/30 text-foreground
                        placeholder-muted-foreground
                        outline-none focus:ring-2 focus:ring-primary
                        transition
                        "
                        />

                        <input
                            data-testid="signin-password"
                            type="password"
                            placeholder="Password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            className="
                        px-5 py-3 rounded-2xl
                        bg-card/30 text-foreground
                        placeholder-muted-foreground
                        outline-none focus:ring-2 focus:ring-primary
                        transition
                        "
                        />

                        {error && (
                            <div className="bg-destructive/30 p-3 rounded-xl text-center text-destructive-foreground text-sm">
                                {error.message}
                            </div>
                        )}

                        <button
                            data-testid="signin-submit"
                            type="submit"
                            disabled={loading}
                            className="
                        mt-4 bg-primary text-primary-foreground
                        font-semibold py-3 rounded-2xl shadow-lg
                        hover:scale-105 hover:bg-primary/80
                        transition-all duration-300
                            disabled:opacity-50
                            "
                        >
                            {loading
                                ? mode === "login"
                                    ? "Logging in..."
                                    : "Creating account..."
                                : mode === "login"
                                    ? "Login"
                                    : "Register"}
                        </button>
                    </form>
                </div>
            </div>
        </>
    )
}

export default SignIn;