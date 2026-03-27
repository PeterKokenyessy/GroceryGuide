import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { Link } from "react-router-dom"

const footerLinks = {
    products: [
        { label: "Category", href: "/category" },
        { label: "Shopping list", href: "/shopping-list" },
    ],
    stores: [
        { label: "Tesco", href: "https://www.tesco.hu/" },
        { label: "Aldi", href: "https://www.aldi.hu/hu/homepage.html" },
        { label: "Lidl", href: "https://www.lidl.hu/" },
        { label: "Spar", href: "https://www.spar.hu/" },
        { label: "Auchan", href: "https://www.reddit.com/r/hungary/comments/oa3hzf/madaras_tesco/" },
    ],
    company: [
        { label: "About us", href: "/about" }, // mi + fotok
        { label: "Careers", href: "/not-working" },
    ],
    legal: [
        { label: "Privacy Policy", href: "/not-working" },
        { label: "Terms and Conditions", href: "/not-working" },
        { label: "Cookies", href: "/not-working" },
    ],
};

export function Footer() {
    return (
        <footer className="bg-background text-foreground">

            {/* TOP SECTION */}
            <div className="border-b border-border/50">
                <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-12">
                    <div className="flex flex-col md:flex-row! md:items-center! justify-between gap-6">
                        <div>
                            <h3 className="text-2xl font-bold mb-2">Stay informed about promotions!</h3>
                            <p className="text-foreground/70">
                                Sign up for our newsletter and never miss a great deal again.
                            </p>
                        </div>

                        <div className="flex flex-col sm:flex-row! gap-3 sm:w-auto! w-full">
                            <Input
                                type="email"
                                placeholder="Your email address"
                                className="bg-background/10 border-border text-foreground placeholder:text-foreground/50 h-12"
                            />
                            <Button className="bg-primary text-primary-foreground hover:bg-primary/90 h-12 px-8">
                                <Link to="not-working" className="flex items-center gap-2">
                                    Subscribe
                                </Link>
                            </Button>
                        </div>
                    </div>
                </div>
            </div>

            {/* MAIN GRID */}
            <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-12">
                <div className="grid grid-cols-2 md:grid-cols-5! gap-6">

                    {/* LOGO */}
                    <div className="col-span-2 md:col-span-1!">
                        <div className="flex items-center gap-2 mb-4">
                            <img
                                src="/images/logo-wide-text.png"
                                alt="Grocery Guide logo"
                                className="rounded-lg object-contain w-auto h-auto max-w-full max-h-full"
                            />
                        </div>
                    </div>

                    {/* LINK COLUMNS */}
                    {Object.entries(footerLinks).map(([category, links]) => (
                        <div key={category}>
                            <h4 className="font-semibold mb-4 capitalize">{category}</h4>
                            <ul className="space-y-3">
                                {links.map((link) => (
                                    <li key={link.label}>
                                        <a
                                            href={link.href}
                                            className="text-sm text-foreground/60 hover:text-foreground transition-colors"
                                        >
                                            {link.label}
                                        </a>
                                    </li>
                                ))}
                            </ul>
                        </div>
                    ))}
                </div>

                {/* BOTTOM ROW */}
                <div className="mt-12 pt-8 border-t border-border/50 flex flex-col sm:flex-row! justify-between items-center gap-4">
                    <p className="text-sm text-foreground/60">
                        © 2026 Grocery Guide. All rights reserved.
                    </p>
                </div>
            </div>
        </footer>
    )
}