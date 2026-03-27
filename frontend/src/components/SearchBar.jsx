import React, { useEffect, useState } from "react";
import { useNavigate, useSearchParams, useLocation } from "react-router-dom";
import { Search } from "lucide-react";
import { Input } from "@/components/ui/input.jsx";
import { Button } from "@/components/ui/button.jsx";

function SearchBar() {
    const navigate = useNavigate();
    const location = useLocation();
    const [searchParams, setSearchParams] = useSearchParams();

    const queryFromUrl = searchParams.get("product") || "";
    const [input, setInput] = useState(queryFromUrl);

    useEffect(() => {
        setInput(queryFromUrl);
    }, [queryFromUrl]);

    const handleSearch = (valueFromClick) => {
        const value = (valueFromClick ?? input).trim();

        // 👉 ha nem category oldalon vagy → redirect
        if (location.pathname !== "/category") {
            navigate(`/category?product=${encodeURIComponent(value)}`);
            return;
        }

        // 👉 ha már category oldalon vagy → update param
        const params = new URLSearchParams(searchParams);

        if (value) {
            params.set("product", value);
        } else {
            params.delete("product");
        }

        params.delete("page");

        setSearchParams(params);
    };

    const handleDelete = () => {
        setInput("")
        handleSearch("")
    }

    return (
        <>
            {/* SEARCH */}
            <div className="flex sm:flex-row gap-3 w-full">
                <div className="relative flex-1">
                    <Search className="absolute left-4 top-1/2 -translate-y-1/2 w-5 h-5 text-muted-foreground" />
                    <Input
                        type="text"
                        placeholder="Search for a product... e.g. milk, bread, apples"
                        value={input}
                        onChange={(e) => setInput(e.target.value)}
                        onKeyDown={(e) => {
                            if (e.key === "Enter") handleSearch();
                        }}
                        className="pl-12 h-14 text-base bg-background border-border"
                    />
                    {input && (
                        <button
                            onClick={handleDelete}
                            className="absolute right-3 top-1/2 -translate-y-1/2
                                        text-muted-foreground hover:text-foreground"
                        >
                            ✕
                        </button>
                    )}
                </div>

                <Button
                    size="lg"
                    className="h-14 px-8 flex items-center gap-2"
                    onClick={handleSearch}
                >
                    <Search className="w-5 h-5" />
                    Search
                </Button>
            </div>

            {/* POPULAR */}
            <div className="flex flex-wrap items-center gap-2">
                <span className="text-sm text-muted-foreground">Popular:</span>

                {["Milk", "Bread", "Chicken breast", "Egg", "Banana"].map((prod) => (
                    <button
                        key={prod}
                        className="px-3 py-1.5 bg-secondary hover:bg-secondary/80 rounded-full text-sm font-medium text-secondary-foreground transition-colors hover:cursor-pointer"
                        onClick={() => {
                            setInput(prod);
                            handleSearch(prod);
                        }}
                    >
                        {prod}
                    </button>
                ))}
            </div>
        </>
    );
}

export default SearchBar;