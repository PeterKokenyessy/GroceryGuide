import { useState } from "react"
import { Button } from "@/components/ui/button.jsx"
import { Moon, Sun } from "lucide-react"

export default function ThemeToggle() {
    const [dark, setDark] = useState(() =>
        window.matchMedia("(prefers-color-scheme: dark)").matches
    )

    const toggleTheme = () => {
        setDark((prev) => {
            const next = !prev
            document.documentElement.classList.toggle("dark", next)
            return next
        })
    }

    return (
        <Button
            variant="ghost"
            onClick={toggleTheme}
            className="rounded-md border px-3 py-2 hover:bg-muted text-primary hover:text-primary cursor-pointer"
        >
            {dark ? <Sun />  : <Moon /> }
        </Button>
    )
}
