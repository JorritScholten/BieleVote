import { BrowserRouter, Route, Routes } from "react-router-dom";
import HomePage from "./page/home";
import LoginPage from "./page/login";
import { AuthProvider } from "./misc/AuthContext";

export default function App() {
  return (
    <>
      <AuthProvider>
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/login" element={<LoginPage />} />
            <Route path="*" element={<h1>Not Found</h1>} />
          </Routes>
        </BrowserRouter>
      </AuthProvider>
    </>
  );
}
