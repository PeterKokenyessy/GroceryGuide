import React from 'react'
import { TrendingDown, Bell, ListChecks, BarChart3 } from "lucide-react"

const features = [
  {
    icon: TrendingDown,
    title: "Price Comparison",
    description:
      "Instantly see which store offers the lowest price for the product you're looking for. Save both time and money.",
  },
  {
    icon: Bell,
    title: "Price Alerts",
    description:
      "Set up notifications and get alerted the moment your favorite product goes on sale.",
  },
  {
    icon: ListChecks,
    title: "Smart Shopping List",
    description:
      "Create your list and let the system show you which store will be the cheapest for your entire purchase.",
  },
  {
    icon: BarChart3,
    title: "Price Analytics",
    description:
      "Track price trends in real time and make sure you always buy at the perfect moment.",
  },
]

function featuresSection() {
  return (
    <section className="py-8 bg-background">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">

        {/* Title */}
        <div className="text-center mb-8">
          <h2 className="text-3xl sm:text-4xl font-bold text-foreground mb-2 text-balance">
            Smart shopping, made easy
          </h2>
          <p className="text-lg text-muted-foreground max-w-2xl mx-auto">
            Everything you need to get the most out of your shopping.
          </p>
        </div>

        {/* Image */}
        <div className="mb-8">
          <img
            src="/images/shopping-baskets.webp"
            alt="Shopping baskets"
            className="w-full h-72 object-contain"
          />
        </div>

        {/* Features Grid */}
        <div className="grid sm:grid-cols-2 lg:grid-cols-4 gap-6">
          {features.map((feature) => {
            const IconComponent = feature.icon
            return (
              <div
                key={feature.title}
                className="group bg-card border border-border rounded-2xl p-6 hover:border-primary/30 hover:shadow-lg hover:shadow-primary/5 transition-all duration-300"
              >
                <div className="w-12 h-12 bg-primary/10 rounded-xl flex items-center justify-center mb-4  transition-colors">
                  <IconComponent className="w-6 h-6 text-primary" />
                </div>
                <h3 className="text-lg font-semibold text-foreground mb-2">{feature.title}</h3>
                <p className="text-sm text-muted-foreground leading-relaxed">{feature.description}</p>
              </div>
            )
          })}
        </div>

      </div>
    </section>
  )
}

export default featuresSection