"use client";

import { useState } from "react";
import Link from "next/link";
import Image from "next/image";
import {
  Star,
  MessageSquare,
  Github,
  Linkedin,
  Instagram,
  X,
  User,
  Settings,
  LogOut,
} from "lucide-react";
import { Button } from "@/components/ui/button";
import { Card, CardContent } from "@/components/ui/card";
import { ScrollArea, ScrollBar } from "@/components/ui/scroll-area";
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogDescription,
  DialogClose,
} from "@/components/ui/dialog";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu";
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";

interface ProductCardProps {
  title: string;
  rating: number;
  comments: number;
  reviews: number;
  image: string;
  onOpenModal: () => void;
}

const ProductCard = ({
  title,
  rating,
  comments,
  reviews,
  image,
  onOpenModal,
}: ProductCardProps) => (
  <Card className="w-[250px] bg-gray-800 text-gray-200 cursor-pointer" onClick={onOpenModal}>
    <CardContent className="p-4">
      <Image
        src={image}
        alt={title}
        width={250}
        height={150}
        className="w-full h-[150px] object-contain mb-4 rounded"
      />
      <h3 className="font-semibold mb-2">{title}</h3>
      <div className="flex items-center gap-2 text-sm text-gray-400">
        <div className="flex">
          {[...Array(5)].map((_, i) => (
            <Star
              key={i}
              className={`w-4 h-4 ${i < rating ? "fill-yellow-500" : "fill-gray-600"}`}
            />
          ))}
        </div>
        <span>{rating}</span>
        <MessageSquare className="w-4 h-4" />
        <span>{comments}</span>
        <span>({reviews} reseñas)</span>
      </div>
    </CardContent>
  </Card>
);

// Tipos para los datos del producto
interface ProductData {
  title: string;
  rating: number;
  comments: number;
  reviews: number;
  image: string;
  description?: string;
  commentsList?: CommentType[];
}

interface ProductSectionProps {
  title: string;
  products: ProductData[];
  onOpenModal: (product: ProductData) => void;
}

const ProductSection = ({ title, products, onOpenModal }: ProductSectionProps) => (
  <section className="mb-12">
    <h2 className="text-2xl font-bold mb-4 text-gray-200">{title}</h2>
    <ScrollArea className="w-full whitespace-nowrap rounded-md border border-gray-700">
      <div className="flex w-max space-x-4 p-4">
        {products.map((product, index) => (
          <ProductCard key={index} {...product} onOpenModal={() => onOpenModal(product)} />
        ))}
      </div>
      <ScrollBar orientation="horizontal" />
    </ScrollArea>
  </section>
);

interface CommentType {
  author: string;
  content: string;
  replies?: CommentType[];
}

// Interfaces para los datos del usuario
export interface UserData {
  name: string;
  image: string;
  email: string;
}

export interface UserMenuProps {
  user: UserData;
  onLogout: () => void;
}
// Componente de menú de usuario
const UserMenu = ({ user, onLogout }: UserMenuProps) => (
  <DropdownMenu>
    <DropdownMenuTrigger asChild>
      <Button variant="ghost" className="relative h-8 w-8 rounded-full">
        <Avatar className="h-8 w-8">
          <AvatarImage src={user.image} alt={user.name} />
          <AvatarFallback>{user.name.charAt(0)}</AvatarFallback>
        </Avatar>
      </Button>
    </DropdownMenuTrigger>
    <DropdownMenuContent className="w-56" align="end" forceMount>
      <DropdownMenuLabel className="font-normal">
        <div className="flex flex-col space-y-1">
          <p className="text-sm font-medium leading-none">{user.name}</p>
          <p className="text-xs leading-none text-muted-foreground">{user.email}</p>
        </div>
      </DropdownMenuLabel>
      <DropdownMenuSeparator />
      <DropdownMenuItem>
        <User className="mr-2 h-4 w-4" />
        <span>Perfil</span>
      </DropdownMenuItem>
      <DropdownMenuItem>
        <Settings className="mr-2 h-4 w-4" />
        <span>Configuración</span>
      </DropdownMenuItem>
      <DropdownMenuSeparator />
      <DropdownMenuItem onClick={onLogout}>
        <LogOut className="mr-2 h-4 w-4" />
        <span>Cerrar sesión</span>
      </DropdownMenuItem>
    </DropdownMenuContent>
  </DropdownMenu>
);

// Componente de comentario
const Comment = ({ author, content, replies }: CommentType) => (
  <div className="mb-4">
    <div className="bg-gray-700 p-3 rounded">
      <h4 className="font-semibold">{author}</h4>
      <p className="text-sm text-gray-300">{content}</p>
    </div>
    {replies && replies.length > 0 && (
      <div className="ml-6 mt-2">
        {replies.map((reply, index) => (
          <Comment key={index} {...reply} />
        ))}
      </div>
    )}
  </div>
);

export default function Component() {
  const [selectedProduct, setSelectedProduct] = useState<ProductData | null>(null);
  const [isLoggedIn, setIsLoggedIn] = useState(true); // Cambiado a true para mostrar el menú de usuario
  const [user, setUser] = useState<UserData | null>({
    name: "Juan Pérez",
    image: "/placeholder.svg?height=32&width=32",
    email: "juan@example.com",
  });

  interface Section {
    title: string;
    products: ProductData[];
  }

  // Datos de ejemplo
  const sections: Section[] = [
    // const sections = [
    {
      title: "Libros",
      products: [
        {
          title: "El gran Gatsby",
          rating: 4,
          comments: 120,
          reviews: 98,
          image: "https://i.imgur.com/5HegeoX.png",
          description: "Una novela clásica sobre el sueño americano en los años 20.",
          commentsList: [
            {
              author: "Lector Ávido",
              content: "Una obra maestra de la literatura americana.",
              replies: [
                {
                  author: "Crítico Literario",
                  content: "Estoy de acuerdo, la prosa de Fitzgerald es incomparable.",
                },
              ],
            },
            {
              author: "Estudiante de Literatura",
              content: "La simbología en este libro es fascinante.",
              replies: [],
            },
          ],
        },
        {
          title: "Cien años de soledad",
          rating: 5,
          comments: 200,
          reviews: 180,
          image: "https://i.imgur.com/xCgxIrt.png",
        },
        {
          title: "1984",
          rating: 4,
          comments: 150,
          reviews: 130,
          image: "https://i.imgur.com/X0SmzCf.png",
        },
        {
          title: "Clean Code",
          rating: 5,
          comments: 320,
          reviews: 300,
          image: "https://i.imgur.com/2wJhVhr.png",
          description: "Una guía de principios y buenas prácticas para escribir código limpio.",
          commentsList: [
            {
              author: "Programador Pro",
              content: "Este libro cambió mi forma de escribir código.",
              replies: [
                {
                  author: "Desarrollador Senior",
                  content: "¡Totalmente de acuerdo! Es una lectura esencial.",
                },
              ],
            },
            {
              author: "Junior Dev",
              content: "He aprendido mucho sobre cómo estructurar mejor mi código.",
              replies: [],
            },
          ],
        },
        {
          title: "Padre Rico Padre Pobre",
          rating: 4,
          comments: 180,
          reviews: 160,
          image: "https://i.imgur.com/qqfv6Xh.png",
          description:
            "Un libro que ofrece lecciones sobre finanzas personales y el desarrollo de la mentalidad emprendedora.",
          commentsList: [
            {
              author: "Emprendedor",
              content: "Este libro me motivó a mejorar mi educación financiera.",
              replies: [
                {
                  author: "Inversionista",
                  content:
                    "Es una excelente introducción para quienes empiezan en el mundo financiero.",
                },
              ],
            },
            {
              author: "Estudiante de Economía",
              content: "Proporciona una perspectiva diferente sobre el dinero y las inversiones.",
              replies: [],
            },
          ],
        },
      ],
    },
    {
      title: "Películas",
      products: [
        {
          title: "El Padrino",
          rating: 5,
          comments: 300,
          reviews: 250,
          image: "https://i.imgur.com/cuGsTi1.jpeg",
        },
        {
          title: "Pulp Fiction",
          rating: 4,
          comments: 280,
          reviews: 220,
          image: "https://i.imgur.com/uv8UZwX.png",
        },
        {
          title: "Forrest Gump",
          rating: 4,
          comments: 260,
          reviews: 200,
          image: "https://i.imgur.com/KBiKw5L.png",
        },
      ],
    },
    {
      title: "Series",
      products: [
        {
          title: "Breaking Bad",
          rating: 5,
          comments: 400,
          reviews: 350,
          image: "https://i.imgur.com/vzLPk6w.png",
        },
        {
          title: "Juego de Tronos",
          rating: 4,
          comments: 500,
          reviews: 450,
          image: "https://i.imgur.com/bEOipeT.png",
        },
        {
          title: "Stranger Things",
          rating: 4,
          comments: 350,
          reviews: 300,
          image: "https://i.imgur.com/8rHj7h4.jpeg",
        },
        {
          title: "Demon Slayer",
          rating: 5,
          comments: 600,
          reviews: 550,
          image: "https://i.imgur.com/yMm78CP.png",
          description:
            "Una emocionante serie de anime que sigue a Tanjiro en su misión para salvar a su hermana y vengar a su familia.",
          commentsList: [
            {
              author: "Fan de Anime",
              content: "La animación es increíble, especialmente en las escenas de batalla.",
              replies: [
                {
                  author: "Crítico de Anime",
                  content: "Ufotable realmente hizo un trabajo impecable con esta serie.",
                },
              ],
            },
            {
              author: "Nuevo en el anime",
              content: "Esta serie me introdujo al mundo del anime y me encantó.",
              replies: [],
            },
          ],
        },
      ],
    },
  ];

  const handleOpenModal = (product: ProductData) => {
    setSelectedProduct(product);
  };

  const handleCloseModal = () => {
    setSelectedProduct(null);
  };

  const handleLogout = () => {
    setIsLoggedIn(false);
    setUser(null);
  };

  return (
    <div className="min-h-screen bg-gray-900 flex flex-col">
      <nav className="flex justify-between items-center p-4 bg-gray-800 shadow-sm">
        <div className="text-2xl font-bold text-gray-200">Logo</div>
        <div className="flex items-center space-x-4">
          {isLoggedIn && user ? (
            <UserMenu user={user} onLogout={handleLogout} />
          ) : (
            <>
              <Button variant="outline" asChild>
                <Link href="/auth/login">Iniciar sesión</Link>
              </Button>
              <Button variant="default" asChild>
                <Link href="/auth/register">Registrarse</Link>
              </Button>
            </>
          )}
        </div>
      </nav>
      <main className="flex-grow">
        <div className="container mx-auto px-4 py-8 max-w-6xl">
          {sections.map((section, index) => (
            <ProductSection key={index} {...section} onOpenModal={handleOpenModal} />
          ))}
        </div>
      </main>
      <footer className="bg-gray-800 text-gray-300 py-8">
        <div className="container mx-auto px-4 max-w-6xl">
          <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
            <div>
              <h3 className="text-lg font-semibold mb-4">Sobre Nosotros</h3>
              <p className="text-sm">
                Somos una plataforma dedicada a proporcionar reseñas y calificaciones de libros,
                películas y series. Nuestra misión es ayudar a los usuarios a descubrir nuevo
                contenido de calidad.
              </p>
            </div>
            <div>
              <h3 className="text-lg font-semibold mb-4">Enlaces Rápidos</h3>
              <ul className="text-sm space-y-2">
                <li>
                  <a href="#" className="hover:text-white">
                    Inicio
                  </a>
                </li>
                <li>
                  <a href="#" className="hover:text-white">
                    Libros
                  </a>
                </li>
                <li>
                  <a href="#" className="hover:text-white">
                    Películas
                  </a>
                </li>
                <li>
                  <a href="#" className="hover:text-white">
                    Series
                  </a>
                </li>
              </ul>
            </div>
            <div>
              <h3 className="text-lg font-semibold mb-4">Redes</h3>
              <div className="flex space-x-4">
                <a
                  href="https://github.com/nicoarbelaez?tab=repositories"
                  className="hover:text-white"
                  target="_blank">
                  <Github />
                </a>
                <a
                  href="https://www.linkedin.com/in/nicolas-arbelaez-tapias/"
                  className="hover:text-white"
                  target="_blank">
                  <Linkedin />
                </a>
                <a
                  href="https://www.instagram.com/nicoo2307/"
                  className="hover:text-white"
                  target="_blank">
                  <Instagram />
                </a>
              </div>
            </div>
          </div>
          <div className="mt-8 text-center text-sm">
            <p>&copy; 2024 Tu Plataforma de Reseñas. Todos los derechos reservados.</p>
          </div>
        </div>
      </footer>

      <Dialog open={!!selectedProduct} onOpenChange={handleCloseModal}>
        <DialogContent className="bg-gray-800 text-gray-200">
          <DialogHeader>
            <DialogTitle>{selectedProduct?.title}</DialogTitle>
            <DialogDescription>
              <div className="flex items-center gap-2 mb-2">
                <div className="flex">
                  {[...Array(5)].map((_, i) => (
                    <Star
                      key={i}
                      className={`w-4 h-4 ${
                        i < (selectedProduct?.rating || 0) ? "fill-yellow-500" : "fill-gray-600"
                      }`}
                    />
                  ))}
                </div>
                <span>{selectedProduct?.rating}</span>
                <span>({selectedProduct?.reviews} reseñas)</span>
              </div>
              <p>{selectedProduct?.description}</p>
            </DialogDescription>
          </DialogHeader>
          <div className="mt-4">
            <h3 className="font-semibold mb-2">Comentarios</h3>
            <ScrollArea className="h-[300px] w-full rounded-md border p-4">
              {selectedProduct?.commentsList?.map((comment, index) => (
                <Comment key={index} {...comment} />
              ))}
            </ScrollArea>
          </div>
          <DialogClose asChild>
            <Button variant="outline" className="mt-4 text-black">
              <X className="mr-2 h-4 w-4" /> Cerrar
            </Button>
          </DialogClose>
        </DialogContent>
      </Dialog>
    </div>
  );
}
