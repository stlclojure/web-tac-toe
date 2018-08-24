(ns web-tac-toe.macros
    (:require [cljs.core.match]))

(defmacro winner? [board-state who]
  `(cljs.core.match/match ~board-state
      [[ ~who ~who ~who ]
       [ ~'_  ~'_  ~'_  ]
       [ ~'_  ~'_  ~'_  ]] true

      [[ ~'_  ~'_  ~'_  ]
       [ ~who ~who ~who ]
       [ ~'_  ~'_  ~'_  ]] true

      [[ ~'_  ~'_  ~'_  ]
       [ ~'_  ~'_  ~'_  ]
       [ ~who ~who ~who ]] true

      [[ ~who ~'_  ~'_  ]
       [ ~who ~'_  ~'_  ]
       [ ~who ~'_  ~'_  ]] true

      [[ ~'_  ~who ~'_  ]
       [ ~'_  ~who ~'_  ]
       [ ~'_  ~who ~'_  ]] true

      [[ ~'_  ~'_  ~who ]
       [ ~'_  ~'_  ~who ]
       [ ~'_  ~'_  ~who ]] true

      [[ ~who ~'_  ~'_  ]
       [ ~'_  ~who ~'_  ]
       [ ~'_  ~'_  ~who ]] true

      [[ ~'_  ~'_  ~who ]
       [ ~'_  ~who ~'_  ]
       [ ~who ~'_  ~'_  ]] true

      :else false))

