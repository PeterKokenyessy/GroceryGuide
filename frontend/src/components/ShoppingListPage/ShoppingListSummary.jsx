import React from 'react'

import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { TrendingDown, Store, ChevronRight } from "lucide-react";
import { Wallet, Sparkles } from "lucide-react";
import { Button } from '@/components/ui/button';
import { useFetch } from '@/helpers/useFetch';


export default function ShoppingListSummary({ data, refrechCart }) {

    const {
        refetch
    } = useFetch('/api/cart/delete', {
        method: "DELETE"
    }, false)

    const handleBuyBtn = async () => {
        await refetch();
        refrechCart()
    }

    return (
        <div className='space-y-4 lg:sticky! lg:top-24! '>

            <Card className="border-primary/20 bg-primary/5 overflow-hidden">

                {/* TOP SUMMARY */}
                <CardContent className="p-6">
                    <div className="flex items-center justify-between">

                        {/* Left side */}
                        <div className="flex items-center gap-3">
                            <div className="flex items-center justify-center w-12 h-12 rounded-xl bg-primary/10">
                                <Wallet className="w-6 h-6 text-primary" />
                            </div>

                            <div>
                                <p className="text-sm text-muted-foreground">
                                    Total Cost
                                </p>
                                <p className="text-3xl font-bold text-primary">
                                    {data.totalPrice.toFixed(2)} €
                                </p>
                            </div>
                        </div>

                        {/* Right badge */}
                        <div className="flex items-center gap-1 text-xs px-3 py-1 rounded-full bg-primary/10 text-primary">
                            <Sparkles className="w-3 h-3" />
                            Summary
                        </div>
                    </div>
                </CardContent>

                {/* SAVINGS */}
                <CardContent className="p-6 border-t border-primary/10">
                    <div className='flex items-center gap-3 mb-4'>
                        <div className='flex items-center justify-center w-12 h-12 rounded-full bg-primary/10'>
                            <TrendingDown className='h-6 w-6 text-primary' />
                        </div>
                        <div>
                            <p className='text-sm text-muted-foreground'>
                                Potential Savings
                            </p>
                            <p className='text-2xl font-bold text-primary'>
                                {data.savedMoney.toFixed(2)} €
                            </p>
                        </div>
                    </div>
                    <p className='text-sm text-muted-foreground'>
                        If you shop at the cheapest store, you can save this amount on your total purchase
                    </p>
                </CardContent>

            </Card>
            {/* Store Comparison */}
            <Card>
                <CardHeader className="pb-4">
                    <CardTitle className="text-base flex items-center gap-2">
                        <Store className='h-5 w-5' />
                        Store List
                    </CardTitle>
                </CardHeader>
                <CardContent className="space-y-3">
                    {data.cart?.map((store) => {

                        return (
                            <div
                                key={store}
                                className={`flex items-center justify-between p-4 rounded-lg transition-colors bg-muted/50 hover:bg-muted`}
                            >
                                <div className='flex items-center gap-3 flex-1'>
                                    <div className={`w-4 h-4 rounded-full shrink-0 ${store?.color}`} />
                                    <div className='flex items-center gap-2'>
                                        <span
                                            className={`font-medium text-foreground"}`}
                                        >
                                            {store.storeName}
                                        </span>
                                    </div>
                                </div>
                                <div className='text-right'>
                                    <p
                                        className={`font-semibold text-foreground"
                                            }`}
                                    >
                                        {store.storeTotal.toFixed(2)} €
                                    </p>
                                </div>
                            </div>
                        )
                    })}
                </CardContent>
            </Card>

            <Card>
                <CardContent className="p-4 space-y-2">
                    <Button className="w-full justify-between" size="lg" onClick={() => handleBuyBtn()}>
                        Checkout
                        <ChevronRight className="h-5 w-5" />
                    </Button>
                </CardContent>
            </Card>

        </div>
    )
}
