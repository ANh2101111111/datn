import Link from "next/link";
import Button from "../common/button";
import Input from "../common/input";
import { Route } from "@/types/route";

const Login = () => {
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
          <Input type="email" placeholder="Username or Email *" />
          <Input type="password" placeholder="Your password" />
          <Button className="w-40 h-16 text-text-medium text-brand-thrid">
            Sign In
          </Button>
        </div>
      </div>
    </div>
  );
};

export default Login;
