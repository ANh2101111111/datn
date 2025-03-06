"use client"
import { useState } from "react";
import Link from "next/link";
import { useRouter } from "next/navigation";
import { Route } from "@/types/route";
import { loginUser } from "@/layout/api/authService.js"; // Import API từ `api.js`
import Button from "../common/button";
import Input from "../common/input";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState<string | null>(null); // Fix lỗi TypeScript
  const router = useRouter();

 const handleLogin = async () => {
   try {
     const data = await loginUser(username, password);
     if (data.token) {
       localStorage.setItem("token", data.token); // Lưu token
       router.push("/"); // Chuyển hướng
     } else {
       setError("Đăng nhập thất bại. Vui lòng thử lại!");
     }
   } catch (err) {
    const errorMessage = err instanceof Error ? err.message : "Đăng nhập thất bại!";
    setError(errorMessage);
  }
  
 };

  return (
    <div className="grid grid-cols-1 lg:grid-cols-2 max-w-[850px] w-full mx-auto gap-12 my-16">
      <div className="hidden lg:block col-span-1 bg-[url(/login.png)] w-full h-[400px] bg-center rounded-xl"></div>
      <div className="col-span-1">
        <p className="text-heading-2 text-text-heading font-bold mb-2 font-quicksand">
          Login
        </p>
        <p className="text-text-muted text-small mb-8">
          Don&apos;t have an account?{" "}
          <Link href={Route.REGISTER}>
            <strong className="text-text-brand1">Create here</strong>
          </Link>
        </p>
        <div className="flex flex-col gap-6">
          <Input
            type="text"
            placeholder="Username or Email *"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
          <Input
            type="password"
            placeholder="Your password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
          {error && <p className="text-red-500">{error}</p>}
          <div className="flex justify-between items-center">
            <Button className="w-40 h-16 text-text-medium text-brand-thrid" onClick={handleLogin}>
              Sign In
            </Button>
            <Link href={Route.FORGOTPASSWORD}>
              <p className="font-lato text-badge-brand-1 text-heading-5">
                Forgot password?
              </p>
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;