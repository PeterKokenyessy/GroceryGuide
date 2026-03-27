import CategoryFilter from '@/components/categoryPage/CategoryFilter';
import CategorySection from '@/components/categoryPage/CategorySection';
import SideFilterSection from '@/components/categoryPage/SideFilterSection';
import { useFetch } from '@/helpers/useFetch';
import LoadingOverlay from '@/components/LoadingOverlay';
import GlobalLoader from '@/components/GlobalLoader';
import { useSearchParams } from "react-router-dom";

import React from 'react';
import PaginationFooter from '../components/categoryPage/PaginationFooter';
import SearchBar from '@/components/SearchBar';

function Category() {

  const [searchParams, setSearchParams] = useSearchParams();

  const DEFAULT_PAGE = 0;
  const DEFAULT_SIZE = 20;
  const size = 20;

  const selectedCategory = searchParams.get("category") || "";
  const selectedBrand = searchParams.getAll("brands");
  const page = Number(searchParams.get("page")) || DEFAULT_PAGE;
  const product = searchParams.get("product") || "";

  const params = new URLSearchParams(searchParams);

  if (page === DEFAULT_PAGE) params.delete("page");
  if (size === DEFAULT_SIZE) params.delete("size");
  if (!product) params.delete("query");

  const url = params.toString() ? `/search?${params.toString()}` : "/search";

  const {
    data: products,
    isFetching: productsFetching
  } = useFetch("/api/products" + url);

  const {
    data: brands,
    loading: brandLoading
  } = useFetch("/api/brands");

  const {
    data: categories,
    loading: categoryLoading
  } = useFetch("/api/categories");


  const isLoading = !products || brandLoading || categoryLoading;

  const handlePageChange = (newPage) => {
    const params = new URLSearchParams(searchParams);

    if (newPage === DEFAULT_PAGE) {
      params.delete("page");
    } else {
      params.set("page", newPage);
    }

    setSearchParams(params);
  };

  const handleSelectCategory = (id) => {
    const params = new URLSearchParams(searchParams);

    if (id === selectedCategory) {
      params.delete("category");
      params.delete("brands");
    } else {
      params.set("category", id);
      params.delete("brands");
    }

    params.delete("page"); // reset page

    setSearchParams(params);
  };

  const handleSelectedBrand = (checked, id) => {

    const params = new URLSearchParams(searchParams);
    const currentBrands = params.getAll("brands");

    let newBrands;

    if (checked) {
      newBrands = [...currentBrands, id];
    } else {
      newBrands = currentBrands.filter((b) => b !== id);
    }

    params.delete("brands");
    newBrands.forEach((b) => params.append("brands", b));

    params.delete("page"); // reset page

    setSearchParams(params);
  };

  return (
    <div className='min-h-screen bg-background relative'>
      <div className='container mx-auto px-4 py-6 max-w-7xl'>

        <div className='space-y-3 mb-6 mt-6'>
          <SearchBar />
        </div>

        <div className='grid grid-cols-1 lg:grid-cols-5! gap-6'>

          {/* left filters */}
          <div className='lg:col-span-1!'>
            <SideFilterSection
              selectedBrand={selectedBrand}
              brands={brands || []}
              handleSelectedBrand={handleSelectedBrand}
            />
          </div>

          <div className='lg:col-span-4!'>
            <CategoryFilter
              categories={categories || []}
              selectedCategories={selectedCategory}
              handleSelectCategories={handleSelectCategory}
            />
            {/*right listproducts */}
            <CategorySection
              products={products?.content || []}
              selectedBrands={selectedBrand}
              selectedCategories={selectedCategory}
            />
          </div>
          <LoadingOverlay show={productsFetching} />
          <GlobalLoader show={isLoading && !products} />
        </div>
        {products && (
          <PaginationFooter
            page={products.number}
            totalPages={products.totalPages}
            setPage={handlePageChange}
          />
        )}
      </div>

    </div>
  )
}

export default Category