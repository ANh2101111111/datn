"use client";
import { useState } from "react";
import { useRouter } from "next/navigation";
import { registerUser } from "@/layout/api/authService";
import Button from "../common/button";
import Input from "../common/input";

const Register = () => {
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [error, setError] = useState("");
  const router = useRouter();

// ✅ API Đăng ký User
const handleRegister = async (e) => {
  e.preventDefault();

  if (password !== confirmPassword) {
    setError("Password confirmation does not match!");
    return;
  }

  try {
    const data = await registerUser(username, email, password, confirmPassword);
    console.log("Response từ API:", data);

    alert("Registration successful! Please log in.");
    router.push("/login");
  } catch (err) {
    console.error("Lỗi đăng ký:", err.message); // Log lỗi chi tiết
    setError(err.message);
  }
};


  return (
    <div className="grid grid-cols-1 lg:grid-cols-1 max-w-[400px] w-full mx-auto gap-12 my-16">
      <div className="col-span-1">
        <p className="text-heading-2 text-text-heading font-semibold mb-8 font-quicksand">
          Create an Account
        </p>
        <p className="text-text-muted text-xs mb-8">
          Your personal data will be used to support your experience throughout
          this website, to manage access to your account, and for other purposes
          described in our privacy policy.
        </p>
        <form onSubmit={handleRegister} className="flex flex-col gap-6">
          <Input
            type="text"
            placeholder="Username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
          <Input
            type="email"
            placeholder="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
          <Input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
          <Input
            type="password"
            placeholder="Confirm Password"
            value={confirmPassword}
            onChange={(e) => setConfirmPassword(e.target.value)}
            required
          />
          {error && <p className="text-red-500">{error}</p>}
          <Button type="submit" className="h-16 text-text-medium text-brand-thrid">
            Submit & Register
          </Button>
        </form>
      </div>
    </div>
  );
};

export default Register;
