import React from 'react'

function CategoryFilter({ categories, selectedCategories, handleSelectCategories }) {
    return (
        <div className="flex flex-wrap gap-2 mb-8">
            {categories && categories.map((category) => (
                <button
                    key={category.id}
                    onClick={() => handleSelectCategories(category.name)}
                    className={`px-4 py-2 rounded-full border border-border text-sm font-medium transition-colors ${selectedCategories === category.name 
                        ? "bg-primary text-primary-foreground"
                        : "bg-card  text-foreground hover:bg-secondary"
                        }`}
                >
                    {category.name}
                </button>
            ))}
        </div>
    )
}

export default CategoryFilter