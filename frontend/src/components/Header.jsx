import React, { useEffect, useState } from 'react'
import { Link, NavLink } from 'react-router-dom'

import { Search, ShoppingCart, Menu, X } from "lucide-react"
import { Button } from './ui/button.jsx'
import ThemeToggle from './Themetoggle'
import { useAuth } from '@/helpers/useAuth'

const links = [{
  name: "Home",
  link: "/"
},
{
  name: "Category",
  link: "category"
},
{
  name: "Shopping List",
  link: "shopping-list"
}]

export default function Header() {

  const [isMenuOpen, setIsMenuOpen] = useState(false)
  const {isAuthenticated,logout} = useAuth();

  return (
    <header className="sticky top-0 z-50 bg-card border-b border-border">
      <div className="max-w-7xl mx-auto px-4">
        <div className="flex items-center justify-between h-20">

          {/* Logo */}
          <div className="flex items-center gap-4">
            <a href="/" className="flex items-center gap-2">
              <div className="h-14 rounded-lg overflow-hidden flex items-center justify-center">
                <img 
                  src="/images/logo-wide-text.png" 
                  alt="Logo" 
                  className="w-full h-full object-contain"
                />
              </div>
            </a>

            <ThemeToggle />
          </div>

          {/* Desktop Navigation */}
          <nav className="hidden md:flex! items-center gap-8 md:gap-8">
            {links.map((link) => (<>
              <NavLink
                key={link.name}
                to={link.link}
                className={({ isActive }) => `text-base font-medium px-3 py-1 hover:text-primary transition-colors ${isActive ? `text-foreground font-semibold` : `text-muted-foreground`} `}
              >
                {link?.name}
              </NavLink>
            </>))}
          </nav>

          {/* Right Side */}
          <div className="flex items-center gap-4">

            <Button data-testid="logout-button" className="hidden md:flex!" onClick={() => {if(isAuthenticated) logout()}}>
              <Link to={isAuthenticated ? "/" : "/signin"} className="flex items-center gap-2">
                {isAuthenticated? "Logout" : "Sign In"}
              </Link>
            </Button>

            {/* Mobile Menu Button */}
            <Button
              variant="ghost"
              size="icon"
              className="flex md:hidden!"
              onClick={() => setIsMenuOpen(!isMenuOpen)}
            >
              {isMenuOpen ? <X className="w-5 h-5" /> : <Menu className="w-5 h-5" />}
            </Button>
          </div>

        </div>

        {/* Mobile Menu */}
        {isMenuOpen && (
          <div className="md:hidden py-4 border-t border-border">
            <nav className="flex flex-col gap-4">
              {links.map(link => (
                <NavLink
                  key={link.name}
                  to={link.link}
                  className={({ isActive }) => `text-sm font-medium  ${isActive ? `text-foreground` : `text-muted-foreground`} `}
                >
                  {link?.name}
                </NavLink>
              ))}
              <Button className="w-full mt-2">
                <Link to="/not-working" className="flex items-center gap-2">
                  Sign in
                </Link>
              </Button>
            </nav>
          </div>
        )}
      </div>
    </header>
  )
}
