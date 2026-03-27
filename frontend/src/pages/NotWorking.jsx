import { useState } from "react";
import gifs from "../data/gifsLinks";
import { Button } from "@/components/ui/button.jsx";
import { BackpackIcon, BadgeCheck, SkipBack, StepBack } from "lucide-react";
import { Link, Navigate } from "react-router-dom";

export default function NotWorking() {
    const [randomGif] = useState(() => {
        const index = Math.floor(Math.random() * gifs.length);
        return gifs[index];
    });

    return (
        <div className="flex justify-center items-center  min-w-screen  min-h-screen">
            <div className="">
                <div className="flex justify-center mb-4 text-destructive font-semibold">Sorry at the moment we don't have this features</div>
                <Button asChild className="mb-4">
                    <Link to="/" className="flex items-center gap-2">
                        <StepBack />
                        Back
                    </Link>
                </Button>

                <img src={randomGif} alt="random gif" width={500} />
            </div>
        </div>
    );
}
