import { Navigate, Route, Routes } from 'react-router-dom'
import './App.css'
import HomePage from './pages/HomePage'
import VisiterLayout from './layout/VisiterLayout'
import ShoppingListPage from './pages/ShoppingListPage'
import CategoryPage from './pages/CategoryPage'
import NotWorking from './pages/NotWorking'
import SignIn from './pages/SignIn'
import ProtectedLayout from './layout/ProtectedLayout'
import { useAuth } from './helpers/useAuth'
import RedirectAfterLogin from './components/RedirectAfterLogin'
import ScrollToTop from './components/ScrollToTop'
import ScrollToTopButton from './components/ScrollToTopButton'
import About from './pages/About'


function App() {
  const { isAuthenticated } = useAuth();

  return (
    <>
      <ScrollToTop />

      <ScrollToTopButton />
      
      <Routes >
        <Route element={<VisiterLayout />}>
          <Route path='/' element={<HomePage />} />
          <Route path='/about' element={<About/>} />
        </Route>

        <Route element={<ProtectedLayout />}>
          <Route path='/shopping-list' element={<ShoppingListPage />} />
          <Route path='/category' element={<CategoryPage />} />
        </Route>

        <Route path='/signin' element={isAuthenticated ? <RedirectAfterLogin /> : <SignIn />}></Route>
        <Route path='/not-working' element={<NotWorking />} />

      </Routes>
    </>
  )
}

export default App
