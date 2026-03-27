import { useFetch } from '@/helpers/useFetch';
import React from 'react'



function StoreLogos() {

  const {
    data: stores
  } = useFetch("/api/store")

  if(!stores) return <></>


  return (
    <section className="bg-background text-foreground py-8">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">

        <p className="text-center text-lg font-medium text-foreground/70 mb-6">
          Price comparison from the largest Hungarian stores
        </p>

        <div className="flex flex-wrap justify-center items-center gap-6 sm:gap-10">
          {stores.map((store) => (
            <div
              key={store.name}
              className="flex items-center gap-2 opacity-80 hover:opacity-100 transition-opacity"
            >
              <div className={`w-8 h-8 ${store.color} rounded-lg flex items-center justify-center`}>
                <span className="text-white text-xs font-bold">
                  {store.name.charAt(0)}
                </span>
              </div>

              <span className="font-medium text-foreground">
                {store.name}
              </span>
            </div>
          ))}
        </div>
      </div>

    </section>
  )
}

export default StoreLogos;