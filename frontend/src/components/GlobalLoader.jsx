import React from 'react'

function GlobalLoader({ show }) {
  return (
    <div
      className={`
        fixed inset-0 z-9999 flex items-center justify-center
        bg-black/40 backdrop-blur-md
        transition-opacity duration-300
        ${show ? "opacity-100" : "opacity-0 pointer-events-none"}
      `}
    >
      <div className="flex gap-3">
        <span className="w-3 h-3 bg-white rounded-full animate-bounce" />
        <span className="w-3 h-3 bg-white rounded-full animate-bounce delay-150" />
        <span className="w-3 h-3 bg-white rounded-full animate-bounce delay-300" />
      </div>
    </div>
  );
}

export default GlobalLoader