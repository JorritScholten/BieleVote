import { BrowserRouter, Route, Routes } from "react-router-dom";
import { AuthProvider } from "./misc/AuthContext";
import { navList } from "./misc/NavMappings";
import PrivateRoute from "./misc/PrivateRoute";

export default function App() {
  return (
    <>
      <AuthProvider>
        <BrowserRouter>
          <Routes>
            {navList.map((nav) =>
              nav.requiresLogin ? (
                <Route
                  key={nav.id}
                  path={nav.path}
                  element={<PrivateRoute>{nav.element()}</PrivateRoute>}
                />
              ) : (
                <Route key={nav.id} path={nav.path} element={nav.element()} />
              )
            )}
            <Route path="*" element={<h1>Not Found</h1>} />
          </Routes>
        </BrowserRouter>
      </AuthProvider>
    </>
  );
}
