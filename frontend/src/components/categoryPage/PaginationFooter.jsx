import React, { useEffect } from 'react'
import { Button } from '../ui/button';
import { ChevronLeft, ChevronRight } from 'lucide-react';
import paginationHelper from '../../helpers/panginationHelper'


function PaginationFooter({ page, totalPages, setPage }) {

  const pages = paginationHelper(page, totalPages);

  useEffect(() => {
    console.log(page)
  }, [page])

  return (
    <div className="flex justify-center items-center gap-2 py-6 border-t mt-6">

      <Button
        onClick={() => setPage(Math.max(page - 1, 0))}
        disabled={page === 0}
      >
        <ChevronLeft />
      </Button>

      {pages.map((p, i) =>
        p === "..." ? (
          <span key={i}>...</span>
        ) : (
          <button
            key={p}
            onClick={() => setPage(p)}
            className={p === page ? "text-primary" : ""}
          >
            {p + 1}
          </button>
        )
      )}

      <Button
        onClick={() => setPage(Math.min(page + 1, totalPages - 1))}
        disabled={page === totalPages - 1}
      >
        <ChevronRight />
      </Button>

    </div>
  );
}

export default PaginationFooter