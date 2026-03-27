import { NotesList } from '@/components/ShoppingListPage/NoteList';
import ShoppingListSummary from '@/components/ShoppingListPage/ShoppingListSummary';
import { useFetch } from '@/helpers/useFetch';
import React, {  useState } from 'react'
import LoadingOverlay from '@/components/LoadingOverlay';

function ShoppingListPage() {
  const {
    data,
    loading,
    refetch: refreshCart
  } = useFetch('api/cart');

  const [cachedData, setCachedData] = useState(null);

  // 🔥 ha jön új data, azonnal cache-eljük (render közben, safe pattern)
  if (data && data !== cachedData) {
    setCachedData(data);
  }

  const displayData = data ?? cachedData;

  if (!displayData) {
    return <div className="p-10">Loading...</div>;
  }

  return (
    <div className='min-h-screen bg-background relative'>

      <div className='container mx-auto px-4 py-6 max-w-6xl'>
        <div className='grid grid-cols-1 lg:grid-cols-3! gap-6'>

          <div className='lg:col-span-2!'>
            <NotesList
              cart={displayData.cart}
              refreshCart={refreshCart}
            />
          </div>

          <div className="lg:col-span-1!">
            <ShoppingListSummary
              potentialSavings={displayData.savedMoney}
              data={displayData}
              refrechCart={refreshCart}
            />
          </div>

        </div>
      </div>

      <LoadingOverlay show={loading} />

    </div>
  );
}

export default ShoppingListPage;