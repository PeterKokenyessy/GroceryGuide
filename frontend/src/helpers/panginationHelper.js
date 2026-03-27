function paginationHelper(currentPage, totalPages, delta = 2) {
  const range = [];
  const rangeWithDots = [];
  let last;

  for (let i = 0; i < totalPages; i++) {
    if (
      i === 0 ||
      i === totalPages - 1 ||
      (i >= currentPage - delta && i <= currentPage + delta)
    ) {
      range.push(i);
    }
  }

  for (let i of range) {
    if (last !== undefined) {
      if (i - last === 2) {
        rangeWithDots.push(last + 1);
      } else if (i - last !== 1) {
        rangeWithDots.push("...");
      }
    }

    rangeWithDots.push(i);
    last = i;
  }

  return rangeWithDots;
}

export default paginationHelper