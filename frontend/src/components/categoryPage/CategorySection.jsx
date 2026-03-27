import React, { useMemo } from 'react'
import { Card, CardHeader } from '../ui/card'
import Products from './Products'
import { useCart } from '@/helpers/useCart';


function CategorySection({ products }) {
        const {addToCart} = useCart();

    return (
        <>
            <section>
                <Card className="overflow-hidden">
                    {products && products.map(p => (
                        <Products key={p.id} product={p} addToCart={addToCart} />
                    ))}
                </Card>
            </section>
        </>
    )
}

export default CategorySection