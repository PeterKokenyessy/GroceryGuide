import React from 'react';
import { TrendingDown, Clock, ShoppingBag } from "lucide-react";
import SearchBar from '../SearchBar';
import { useFetch } from '@/helpers/useFetch';
import CountUp from "react-countup";

function HeroSection() {

    const {
        data,
        loading
    } = useFetch('/api/stat/basic')

    return (
        <section className="relative overflow-hidden bg-background">

            <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 pt-10 pb-4 lg:pt-16 lg:pb-6">

                {/* Wrapper */}
                <div className="grid lg:grid-cols-2 gap-16 items-center">

                    {/* HERO CONTENT */}
                    <div className="space-y-16">
                        <div className="space-y-8 max-w-3xl w-full">
                            <div className="space-y-4">
                                <h1 className="text-4xl sm:text-5xl lg:text-6xl font-bold text-foreground leading-tight text-balance">
                                    Compare the{" "}
                                    <span className="text-primary">food prices</span>{" "}
                                    in one place
                                </h1>

                                <p className="text-lg text-muted-foreground max-w-lg leading-relaxed">
                                    Search for any product and find the best price from the largest Global stores.
                                </p>
                            </div>

                            <SearchBar />

                        </div>
                    </div>

                    {/* RIGHT SIDE IMAGE */}
                    <div className="flex justify-center lg:justify-end">
                        <img
                            src="/images/shopping-cart.webp"
                            alt="Shopping illustration"
                            className="w-full object-contain rounded-lg"
                        />
                    </div>
                </div>

                {/* STATS */}
                <div className="mt-16 flex flex-row gap-4 lg:gap-6">

                    <div className="flex-1 bg-primary/5 border border-primary/10 rounded-2xl p-6 space-y-2">
                        <div className="flex items-center gap-3">
                            <div className="w-12 h-12 bg-primary/10 rounded-xl flex items-center justify-center">
                                <ShoppingBag className="w-6 h-6 text-primary" />
                            </div>
                            <div>
                                <p className="text-3xl font-bold text-foreground">
                                    <CountUp end={data?.product} duration={8}/>
                                </p>
                                <p className="text-sm text-muted-foreground">Compared product</p>
                            </div>
                        </div>
                    </div>

                    <div className="flex-1 bg-primary/5 border border-primary/10 rounded-2xl p-6 space-y-2">
                        <div className="flex items-center gap-3">
                            <div className="w-12 h-12 bg-primary/10 rounded-xl flex items-center justify-center">
                                <TrendingDown className="w-6 h-6 text-primary" />
                            </div>
                            <div>
                                <p className="text-3xl font-bold text-foreground">
                                    <CountUp end={data?.store} duration={9} delay={2} />
                                </p>

                                <p className="text-sm text-muted-foreground">Store offerings</p>
                            </div>
                        </div>
                    </div>

                    <div className="flex-1 bg-primary/5 border border-primary/10 rounded-2xl p-6 space-y-2">
                        <div className="flex items-center gap-3">
                            <div className="w-12 h-12 bg-primary/10 rounded-xl flex items-center justify-center">
                                <Clock className="w-6 h-6 text-primary" />
                            </div>
                            <div>
                                <p className="text-3xl font-bold text-foreground">Daily</p>
                                <p className="text-sm text-muted-foreground">Updated prices</p>
                            </div>
                        </div>
                    </div>

                </div>

            </div>
        </section>
    );
}

export default HeroSection;