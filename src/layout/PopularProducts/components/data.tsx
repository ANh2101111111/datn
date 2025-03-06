export interface IProductDetail {
  id: number;
  name: string;
  imageUrl: string;
  rating: number;
  originalPrice: number;
  discountedPrice: number;
  weight: string;
  brand: string;
  type: string;
  textType: string;
  description: React.ReactNode;
}

const productData = [
  {
    id: 1,
    name: "All Natural Italian-Style Chicken Meatballs",
    imageUrl:
      "https://giant.vn/wp-content/uploads/2020/07/talon-1-2021-front-3.jpg",
    rating: 5,
    originalPrice: 2.8,
    discountedPrice: 2.51,
    weight: "500 gram",
    brand: "Hodo Foods",
    type: "NEW",
    textType: "HOT",
    description: (
      <>
        Uninhibited carnally hired played in whimpered dear gorilla koala
        depending and much yikes off far quetzal goodness and from for grimaced
        goodness unaccountably and meadowlark near unblushingly crucial scallop
        tightly neurotic hungrily some and dear furiously this apart.
        <br />
        <br />
        Spluttered narrowly yikes left moth in yikes bowed this that grizzly
        much hello on spoon-fed that alas rethought much decently richly and wow
        against the frequent fluidly at formidable acceptably flapped besides
        and much circa far over the bucolically hey precarious goldfinch
        mastodon goodness gnashed a jellyfish and one however because.
        <br />
        <br />
        Less lion goodness that euphemistically robin expeditiously bluebird
        smugly scratched far while thus cackled sheepishly rigid after due one
        assenting regarding censorious while occasional or this more crane went
        more as this less much amid overhung anathematic because much held one
        exuberantly sheep goodness so where rat wry well concomitantly.
      </>
    ),
  },
  {
    id: 2,
    name: "All Natural Italian-Style Chicken Meatballs",
    imageUrl: "https://giant.vn/wp-content/uploads/2021/11/talon-5-2022.jpg",
    rating: 5,
    originalPrice: 2.8,
    discountedPrice: 2.51,
    weight: "500 gram",
    brand: "Hodo Foods",
    type: "HOT",
    textType: "HOT",
    description: (
      <>
        Uninhibited carnally hired played in whimpered dear gorilla koala
        depending and much yikes off far quetzal goodness and from for grimaced
        goodness unaccountably and meadowlark near unblushingly crucial scallop
        tightly neurotic hungrily some and dear furiously this apart.
        <br />
        <br />
        Spluttered narrowly yikes left moth in yikes bowed this that grizzly
        much hello on spoon-fed that alas rethought much decently richly and wow
        against the frequent fluidly at formidable acceptably flapped besides
        and much circa far over the bucolically hey precarious goldfinch
        mastodon goodness gnashed a jellyfish and one however because.
        <br />
        <br />
        Less lion goodness that euphemistically robin expeditiously bluebird
        smugly scratched far while thus cackled sheepishly rigid after due one
        assenting regarding censorious while occasional or this more crane went
        more as this less much amid overhung anathematic because much held one
        exuberantly sheep goodness so where rat wry well concomitantly.
      </>
    ),
  },
  {
    id: 3,
    name: "All Natural Italian-Style Chicken Meatballs",
    imageUrl: "https://giant.vn/wp-content/uploads/2021/10/talon-4-2022-5.jpg",
    rating: 5,
    originalPrice: 2.8,
    discountedPrice: 2.51,
    weight: "500 gram",
    brand: "Hodo Foods",
    type: "HOT",
    textType: "HOT",
    description: (
      <>
        Uninhibited carnally hired played in whimpered dear gorilla koala
        depending and much yikes off far quetzal goodness and from for grimaced
        goodness unaccountably and meadowlark near unblushingly crucial scallop
        tightly neurotic hungrily some and dear furiously this apart.
        <br />
        <br />
        Spluttered narrowly yikes left moth in yikes bowed this that grizzly
        much hello on spoon-fed that alas rethought much decently richly and wow
        against the frequent fluidly at formidable acceptably flapped besides
        and much circa far over the bucolically hey precarious goldfinch
        mastodon goodness gnashed a jellyfish and one however because.
        <br />
        <br />
        Less lion goodness that euphemistically robin expeditiously bluebird
        smugly scratched far while thus cackled sheepishly rigid after due one
        assenting regarding censorious while occasional or this more crane went
        more as this less much amid overhung anathematic because much held one
        exuberantly sheep goodness so where rat wry well concomitantly.
      </>
    ),
  },
  {
    id: 4,
    name: "All Natural Italian-Style Chicken Meatballs",
    imageUrl: "https://somings.vn/wp-content/uploads/2022/03/lin-51-1.png",
    rating: 3,
    originalPrice: 2.8,
    discountedPrice: 2.51,
    weight: "500 gram",
    brand: "Hodo Foods",
    type: "HOT",
    textType: "HOT",
    description: (
      <>
        Uninhibited carnally hired played in whimpered dear gorilla koala
        depending and much yikes off far quetzal goodness and from for grimaced
        goodness unaccountably and meadowlark near unblushingly crucial scallop
        tightly neurotic hungrily some and dear furiously this apart.
        <br />
        <br />
        Spluttered narrowly yikes left moth in yikes bowed this that grizzly
        much hello on spoon-fed that alas rethought much decently richly and wow
        against the frequent fluidly at formidable acceptably flapped besides
        and much circa far over the bucolically hey precarious goldfinch
        mastodon goodness gnashed a jellyfish and one however because.
        <br />
        <br />
        Less lion goodness that euphemistically robin expeditiously bluebird
        smugly scratched far while thus cackled sheepishly rigid after due one
        assenting regarding censorious while occasional or this more crane went
        more as this less much amid overhung anathematic because much held one
        exuberantly sheep goodness so where rat wry well concomitantly.
      </>
    ),
  },
  {
    id: 5,
    name: "All Natural Italian-Style Chicken Meatballs",
    imageUrl:
      "https://xedaphocsinh.vn/image/THongNhat2023/XedapHocsinh-thong-nhat-mtb-26-02-xanh-720x480.jpg",
    rating: 5,
    originalPrice: 2.8,
    discountedPrice: 2.51,
    weight: "500 gram",
    brand: "Hodo Foods",
    type: "SALE",
    textType: "50%",
    description: (
      <>
        Uninhibited carnally hired played in whimpered dear gorilla koala
        depending and much yikes off far quetzal goodness and from for grimaced
        goodness unaccountably and meadowlark near unblushingly crucial scallop
        tightly neurotic hungrily some and dear furiously this apart.
        <br />
        <br />
        Spluttered narrowly yikes left moth in yikes bowed this that grizzly
        much hello on spoon-fed that alas rethought much decently richly and wow
        against the frequent fluidly at formidable acceptably flapped besides
        and much circa far over the bucolically hey precarious goldfinch
        mastodon goodness gnashed a jellyfish and one however because.
        <br />
        <br />
        Less lion goodness that euphemistically robin expeditiously bluebird
        smugly scratched far while thus cackled sheepishly rigid after due one
        assenting regarding censorious while occasional or this more crane went
        more as this less much amid overhung anathematic because much held one
        exuberantly sheep goodness so where rat wry well concomitantly.
      </>
    ),
  },
];
export default productData;
