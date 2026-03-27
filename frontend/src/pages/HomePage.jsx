import HeroSection from '../components/homePage/HeroSection.jsx';
import FeaturesSection from '../components/homePage/FeaturesSection.jsx';
import HowItWork from '../components/homePage/HowItWork.jsx';
import StoreLogos from '../components/homePage/StoreLogos.jsx';

function HomePage() {
  return (
    <div className="min-h-screen flex flex-col">

        <HeroSection />
        <FeaturesSection />
        <StoreLogos/>
        <HowItWork/>
 
    </div>
  );
}

export default HomePage;
