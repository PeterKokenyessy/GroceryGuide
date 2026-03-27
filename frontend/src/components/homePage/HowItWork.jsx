import React from 'react'
import { Search, BarChart2, ShoppingCart, Smile } from "lucide-react"

const steps = [
  {
    number: "1",
    icon: Search,
    title: "Search for a product",
    description: "Enter the name of the product you are looking for or select a category.",
  },
  {
    number: "2",
    icon: BarChart2,
    title: "Compare",
    description: "Check prices in all supported stores at once.",
  },
  {
    number: "3",
    icon: ShoppingCart,
    title: "Plan your shopping",
    description: "Create a shopping list and optimize your costs.",
  },
  {
    number: "4",
    icon: Smile,
    title: "Save money",
    description: "Enjoy savings and more efficient shopping!",
  },
]

function HowItWork() {
  return (
    <section className="py-10 bg-background text-foreground">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">

        {/* HEADER */}
        <div className="text-center mb-16">
          <h2 className="text-3xl sm:text-4xl font-bold mb-4">
            How does it work?
          </h2>
          <p className="text-lg text-muted-foreground max-w-2xl mx-auto">
            Start saving in four simple steps
          </p>
        </div>

        {/* STEPS */}
        <div className="grid sm:grid-cols-2 lg:grid-cols-4 gap-8">
          {steps.map((step, index) => {
            const IconComponent = step.icon
            return (
              <div key={step.number} className="relative text-center">

                {/* CONNECTING LINE (DESKTOP ONLY) */}
                {index < steps.length - 1 && (
                  <div className="hidden lg:block absolute top-10 left-[60%] w-[80%] h-0.5 bg-border" />
                )}

                {/* ICON + NUMBER */}
                <div className="relative z-10 mx-auto w-20 h-20 bg-primary/10 rounded-2xl flex items-center justify-center mb-6">
                  <IconComponent className="w-8 h-8 text-primary" />
                  <div className="absolute -top-2 -right-2 w-7 h-7 bg-primary rounded-full flex items-center justify-center">
                    <span className="text-sm font-bold text-primary-foreground">{step.number}</span>
                  </div>
                </div>

                {/* TITLE + DESCRIPTION */}
                <h3 className="text-lg font-semibold mb-2">{step.title}</h3>
                <p className="text-sm text-muted-foreground">{step.description}</p>
              </div>
            )
          })}
        </div>

      </div>
    </section>
  )
}

export default HowItWork;