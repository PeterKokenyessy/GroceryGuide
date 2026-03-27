import React from 'react'
import { Navigate, Outlet, useLocation } from 'react-router-dom'
import Header from '../components/Header'
import { Footer } from '../components/Footer'
import { useAuth } from '@/helpers/useAuth'

function ProtectedLayout() {

  const {isAuthenticated} = useAuth();
  const location = useLocation();
 
  console.log(isAuthenticated)
  if(!isAuthenticated) {
    return <Navigate to="/signin" state={{from : location }} replace/>
  }
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

export default ProtectedLayout