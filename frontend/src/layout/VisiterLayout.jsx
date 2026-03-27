import React from 'react'
import { Navigate, Outlet } from 'react-router-dom'
import Header from '../components/Header'
import { Footer } from '../components/Footer'

function VisiterLayout() {

  return (
    <>
    <Header />
    <main className='min-h-screen'>
        <Outlet />
    </main>
    <Footer />
    </>
  )
}

export default VisiterLayout