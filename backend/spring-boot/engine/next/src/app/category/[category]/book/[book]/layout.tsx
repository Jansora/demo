import {loadBook, metadataCache} from "@/lib/config/initConfig";
import {metadata} from "@/lib/head";
import {Book, Category} from "@/lib/declares/config";
import RenderMenu from "@/app/category/[category]/book/[book]/RenderMenu";

export async function generateMetadata({ params }) {
    const {category, book} = params;

    const categoryFound: Category = metadataCache.categoryCache[category];
    const bookFound: Book = metadataCache.bookCache[book];

    if (!categoryFound || !bookFound) {
        console.log("book 404", categoryFound, bookFound)

        return metadata({})
    }

    return metadata({
        title: `${bookFound.name} | ${categoryFound.name}`,
        description: `${bookFound.name} | ${categoryFound.name}`,
    })

}

export default function Layout({children, params}) {

    const { category, book } = params;

    const bookCache = loadBook(book);
    const bookData = bookCache && bookCache.menu ? bookCache.menu : []


    return (
        <>
            <RenderMenu data={bookData}>
                {children}
            </RenderMenu>

        </>
    )
}
