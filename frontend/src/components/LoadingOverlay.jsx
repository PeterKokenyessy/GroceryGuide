import React from 'react'

function LoadingOverlay({ show }) {
    return (
        <div
            className={`
                        absolute inset-0 z-50 flex items-center justify-center
                        bg-black/40 backdrop-blur-sm
                        transition-opacity duration-300
                        ${show ? "opacity-100" : "opacity-0 pointer-events-none"}
            `}
        >
            <div className="flex gap-2">
                <span className="w-2 h-2 bg-white rounded-full animate-bounce" />
                <span className="w-2 h-2 bg-white rounded-full animate-bounce delay-150" />
                <span className="w-2 h-2 bg-white rounded-full animate-bounce delay-300" />
            </div>
        </div>
    );
}

export default LoadingOverlay