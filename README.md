# cv

development
```sh
npx shadow-cljs watch frontend
```

## production

locally (need [babashka](https://book.babashka.org/#_installation))
```sh
npx shadow-cljs release frontend
bb serve.clj --dir public
```

deploy to github-pages
```sh
bb deploy.clj
```