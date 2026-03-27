import React from 'react'
import NoteItem from './NoteItem';

import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import {
  Circle,
  ShoppingBag,
  CheckCircle2,
} from "lucide-react";

export function NotesList({ cart, refreshCart }) {

  const isEmpty = cart.every(store => store.items.length === 0);

  return (
    <Card className="overflow-hidden">
      <CardHeader className="border-b border-border/50 bg-muted/30">
        <div className="flex items-center justify-between">
          <CardTitle className="flex items-center gap-2 text-lg">
            <ShoppingBag className="h-5 w-5 text-primary" />
            <span style={{ fontFamily: 'var(--font-handwriting)' }} className="text-2xl">
              Shopping List
            </span>
          </CardTitle>
        </div>
      </CardHeader>

      <CardContent className="p-0">

        {/* Active items */}
        <div className="divide-y-0">
          {isEmpty ? (
            <div className="py-12 text-center">
              <Circle className="h-12 w-12 mx-auto text-muted-foreground/30 mb-3" />
              <p style={{ fontFamily: 'var(--font-handwriting)' }} className="text-muted-foreground text-lg">
                The list is empty
              </p>
              <p className="text-sm text-muted-foreground/70">
                Add items to get started
              </p>
            </div>
          ) : (
            <div className="px-2">

              {cart.map(store => (
                <div key={store.storeName}>
                  <h2 className="font-semibold px-2 py-1">{store.storeName}</h2>

                  {store.items.map(item =>
                    !item.checked && (
                      <NoteItem
                        key={item.id}
                        item={item}
                        store={store.storeName}
                        refreshCart={refreshCart}
                      />
                    )
                  )}
                </div>
              ))}

            </div>
          )}
        </div>

        {/* Checked items */}
        <div className="border-t border-border/50">
          <div className="px-4 py-3 bg-muted/30 flex items-center gap-2">
            <CheckCircle2 className="h-4 w-4 text-primary" />
          </div>

          <div className="px-2 bg-muted/10">

            {cart.map(store => (
              <div key={store.storeName + "-checked"}>
                {store.items.map(item =>
                  item.checked && (
                    <NoteItem
                      key={item.id}
                      item={item}
                      refreshCart={refreshCart}
                    />
                  )
                )}
              </div>
            ))}

          </div>
        </div>

      </CardContent>
    </Card>
  );
}